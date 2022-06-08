package com.startblog.sys.role.service.impl;

import com.startblog.sys.permission.bean.Permission;
import com.startblog.sys.permission.service.PermissionInfoManager;
import com.startblog.sys.role.bean.Role;
import com.startblog.sys.role.dao.RoleDao;
import com.startblog.sys.role.dao.RoleUserDao;
import com.startblog.sys.role.entity.RoleEntity;
import com.startblog.sys.role.entity.RoleUserEntity;
import com.startblog.sys.role.service.RoleInfoManager;
import com.startblog.sys.user.bean.User;
import com.startblog.sys.user.service.UserInfoManager;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Service("roleInfoManager")
public class RoleInfoManagerImpl implements RoleInfoManager, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;
    @Autowired
    @Qualifier("roleUserDao")
    private RoleUserDao roleUserDao;
    @Autowired
    @Qualifier("userInfoManager")
    private UserInfoManager userInfoManager;
    @Autowired
    @Qualifier("permissionInfoManager")
    private PermissionInfoManager permissionInfoManager;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(roleDao, "roleDao is required!");
        Assert.notNull(roleUserDao, "roleUserDao is required!");
        Assert.notNull(userInfoManager, "userInfoManager is required!");
    
    }
    
    @Override
    @Transactional
    public Role createRole(Role role) {
        //RoleEntity entity = new RoleEntity();
        //BeanUtils.copyProperties(role, entity);
        Assert.notNull(role, "role is null");
        String code = role.getRoleCode();
        String name = role.getRoleName();
        if(StringUtils.isBlank(code)){
            throw new IllegalArgumentException("role code must not be null");
        }else if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("role name must not be null");
        }else if(!CollectionUtils.isEmpty(roleDao.findByRoleCode(code))){
            logger.error("role {} already exists!", code);
            throw new DuplicateKeyException("role " + code + " already exists!");
        }
        RoleEntity entity = roleDao.merge(new RoleEntity(role));
        return new Role(entity);
    }
    
    @Override
    @Transactional
    public Role updateRole(Role role) {
        Assert.notNull(role, "role must not be null");
        Optional<RoleEntity> entity = roleDao.findById(role.getId());
        return entity.map((item) -> {
            Role oldValue = new Role(item);
            // todo 后续添加事件
            item.setRoleCode(role.getRoleCode());
            item.setRoleName(role.getRoleName());
            item.setRoleDescription(role.getRoleDescription());
            item = roleDao.mergeAndFlush(item);
            Role newValue = new Role(item);
            // todo 后续添加事件
            return newValue;
        }).orElseGet(() -> {
            logger.warn("role {} not found!", role.getId());
            return null;
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        Optional<RoleEntity> entity = roleDao.findById(id);
        return entity.map((item) -> new Role(item)).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Role getRole(String code) {
        List<RoleEntity> entities = roleDao.findByRoleCode(code);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return new Role(entities.get(0));
    }
    
    @Override
    @Transactional
    public void removeRole(Role role) {
        Assert.notNull(role, "role must not be null");
        Long id = role.getId();
        Optional<RoleEntity> entity = roleDao.findById(id);
        entity.ifPresent((item) -> {
            // todo 后续添加事件
            List<RoleUserEntity> userList = roleUserDao.findByRoleEntityId(id);
            roleUserDao.deleteAll(userList);
            roleUserDao.flush();
            permissionInfoManager.reassignPermission(new Role(item), null);
            roleDao.delete(item);
            roleDao.flush();
            // todo 后续添加事件
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public User[] getAssignedUsers(Role role) {
        Assert.notNull(role, "role must not be null");
        Optional<RoleEntity> roleEntity = roleDao.findById(role.getId());
        return roleEntity.map((item) -> {
            List<RoleUserEntity> entities = roleUserDao.findByRoleEntityId(item.getId());
            User[] userArray = new User[entities.size()];
            for(int i = 0; i < entities.size(); ++i) {
                RoleUserEntity entity = entities.get(i);
                userArray[i] = userInfoManager.getUserById(entity.getUserId());
            }
            return userArray;
        }).orElseGet(() -> {
            logger.warn("role \"{}\" not found!", role.getId());
            return new User[0];
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Role[] getAssignedRoles(User user) {
        Assert.notNull(user, "user must not be null");
        List<RoleUserEntity> entities = roleUserDao.findByUserId(user.getId());
        Role[] roleArray = new Role[entities.size()];
        for (int i = 0; i < entities.size(); i++) {
            roleArray[i] = new Role(entities.get(i).getRoleEntity());
        }
        return roleArray;
    }
    
    @Override
    @Transactional
    public void assignUser(Role role, User[] users) {
        Assert.notNull(role, "role must not be null");
        Assert.notNull(users, "users must not be null");
        RoleEntity roleEntity = roleDao.findById(role.getId()).<IllegalArgumentException>orElseThrow(() -> {
            logger.error("role {} not found!", role.getId());
            throw new IllegalArgumentException("role " + role.getId() + " not found!");
        });
        Iterator<RoleUserEntity> iterator = roleUserDao.findByRoleEntityId(roleEntity.getId()).iterator();
        
        List<Long> included = new ArrayList<>();
        List<Long> grantIds = new ArrayList();
        while (iterator.hasNext()){
            included.add(iterator.next().getUserId());
        }
        
        for (int i = 0; i < users.length; i++) {
            Long userId = users[i].getId();
            if(!included.contains(userId)){
                included.add(userId);
                RoleUserEntity entity = new RoleUserEntity();
                entity.setUserId(userId);
                entity.setRoleEntity(roleEntity);
                roleUserDao.persist(entity);
                grantIds.add(userId);
            }
        }
        roleUserDao.flush();
        
        // todo 后续添加事件
    }
    
    @Override
    @Transactional
    public void deassignUser(Role role, User[] users) {
        Assert.notNull(role, "role must not be null");
        Assert.notNull(users, "users must not be null");
        
        List<Long> grantIds = new ArrayList();
        List<Long> revokeIds = new ArrayList();
        Long roleId = role.getId();
        for (User user : users) {
            Long userId = user.getId();
            RoleUserEntity entity = roleUserDao.findByRoleEntityIdAndUserId(roleId, userId);
            if(entity != null){
                revokeIds.add(userId);
                roleUserDao.delete(entity);
            }
        }
        roleUserDao.flush();
        // todo 后续添加事件
    
    }
    
    @Override
    @Transactional
    public void reassignUser(Role role, User[] users) {
        Assert.notNull(role, "role must not be null");
        Assert.notNull(users, "users must not be null");
    
        long roleId = role.getId();
        RoleEntity roleEntity = roleDao.findById(roleId).<IllegalArgumentException>orElseThrow(() -> {
            logger.warn("role {} not found!", roleId);
            throw new IllegalArgumentException("role " + roleId + " not found!");
        });
        Map<Long, RoleUserEntity> toBeDeleted = new LinkedHashMap();
        Iterator<RoleUserEntity> iterator = roleUserDao.findByRoleEntityId(roleId).iterator();
        while(iterator.hasNext()) {
            RoleUserEntity next = iterator.next();
            toBeDeleted.put(next.getUserId(), next);
        }
        
        List<Long> grantIds = new ArrayList();
        List<Long> revokeIds = new ArrayList();
        Map<Long, RoleUserEntity> included = new LinkedHashMap();
        for (User user : users) {
            Long userId = user.getId();
            if (!included.containsKey(userId)) {
                RoleUserEntity entity = toBeDeleted.remove(userId);
                if (entity == null) {
                    entity = new RoleUserEntity();
                    entity.setUserId(userId);
                    entity.setRoleEntity(roleEntity);
                    entity = roleUserDao.merge(entity);
                    grantIds.add(userId);
                }
        
                included.put(userId, entity);
            }
        }
        Iterator<Map.Entry<Long, RoleUserEntity>> entryIterator = toBeDeleted.entrySet().iterator();
        while(entryIterator.hasNext()) {
            Map.Entry<Long, RoleUserEntity> next = entryIterator.next();
            RoleUserEntity entity = next.getValue();
            revokeIds.add(entity.getUserId());
            roleUserDao.delete(entity);
        }
    
        roleUserDao.flush();
        // todo 后续添加事件
    
    }
    
    @Override
    @Transactional
    public void reassignRole(User user, Role[] roles) {
        Assert.notNull(user, "user must not be null");
        Long userId = user.getId();
        if (userInfoManager.getUserById(userId) == null) {
            logger.error("user {} not found!", userId);
            throw new IllegalArgumentException("user " + userId + " not found!");
        } else {
            Map<Long, RoleUserEntity> toBeDeleted = new LinkedHashMap();
            Iterator<RoleUserEntity> iterator = roleUserDao.findByUserId(userId).iterator();
            while (iterator.hasNext()){
                RoleUserEntity next = iterator.next();
                toBeDeleted.put(next.getRoleEntity().getId(), next);
            }
    
            List<Role> grants = new ArrayList();
            List<Role> revokes = new ArrayList();
            Map<Long, RoleUserEntity> included = new LinkedHashMap();
            if(roles != null){
                for (Role role : roles) {
                    Long roleId = role.getId();
                    if (!included.containsKey(roleId)) {
                        RoleUserEntity entity = toBeDeleted.remove(roleId);
                        if (entity == null) {
                            entity = new RoleUserEntity();
                            entity.setUserId(userId);
                            RoleEntity roleEntity = roleDao.findById(roleId).orElse(null);
                            if (roleEntity == null) {
                                logger.warn("role {} not found!", roleId);
                                continue;
                            }
                
                            entity.setRoleEntity(roleEntity);
                            entity = roleUserDao.merge(entity);
                            grants.add(new Role(entity.getRoleEntity()));
                        }
            
                        included.put(roleId, entity);
                    }
                }
            }
            Iterator<Map.Entry<Long, RoleUserEntity>> toBeDeletedIterator = toBeDeleted.entrySet().iterator();
            while(toBeDeletedIterator.hasNext()) {
                Map.Entry<Long, RoleUserEntity> entry = toBeDeletedIterator.next();
                RoleUserEntity entity = entry.getValue();
                revokes.add(new Role(entity.getRoleEntity()));
                roleUserDao.delete(entity);
            }
            roleUserDao.flush();
            // todo 后续添加事件
        }
    }
    
    
}
