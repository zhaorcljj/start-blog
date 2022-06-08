package com.startblog.sys.permission.service.impl;

import com.startblog.sys.permission.bean.Permission;
import com.startblog.sys.permission.dao.PermissionDao;
import com.startblog.sys.permission.dao.PermissionRoleDao;
import com.startblog.sys.permission.entity.PermissionEntity;
import com.startblog.sys.permission.entity.PermissionRoleEntity;
import com.startblog.sys.permission.service.PermissionInfoManager;
import com.startblog.sys.role.bean.Role;
import com.startblog.sys.role.dao.RoleDao;
import com.startblog.sys.role.entity.RoleUserEntity;
import com.startblog.sys.role.service.RoleInfoManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
@Service("permissionInfoManager")
public class PermissionInfoManagerImpl implements PermissionInfoManager, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("permissionDao")
    private PermissionDao permissionDao;
    @Autowired
    @Qualifier("permissionRoleDao")
    private PermissionRoleDao permissionRoleDao;
    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(permissionDao, "permissionDao is required!");
        Assert.notNull(permissionRoleDao, "permissionRoleDao is required!");
        Assert.notNull(roleDao, "roleDao is required!");
    }
    
    
    @Override
    @Transactional
    public Permission create(Permission permission) {
        Assert.notNull(permission, "permission is null");
        String code = permission.getPermissionCode();
        String name = permission.getPermissionName();
        if(StringUtils.isBlank(code)){
            throw new IllegalArgumentException("permission code must not be null");
        }else if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("permission name must not be null");
        }else if(!CollectionUtils.isEmpty(permissionDao.findByPermissionCode(code))){
            logger.error("permission {} already exists!", code);
            throw new DuplicateKeyException("permission " + code + " already exists!");
        }
        PermissionEntity entity = permissionDao.merge(new PermissionEntity(permission));
        return new Permission(entity);
    }
    
    @Override
    @Transactional
    public Permission update(Permission permission) {
        Assert.notNull(permission, "permission is null");
        Optional<PermissionEntity> entity = permissionDao.findById(permission.getId());
        return entity.map((item) -> {
            Permission oldValue = new Permission(item);
            // todo 后续添加事件
            item.setPermissionCode(permission.getPermissionCode());
            item.setPermissionName(permission.getPermissionName());
            item.setPermissionDescription(permission.getPermissionDescription());
            item = permissionDao.merge(item);
            Permission newValue = new Permission(item);
            // todo 后续添加事件
            return newValue;
        }).orElseGet(() -> {
            logger.warn("permission {} not found!", permission.getId());
            return null;
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Permission getPermissionById(Long id) {
        Optional<PermissionEntity> entity = permissionDao.findById(id);
        return entity.map((item) -> new Permission(item)).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Permission getPermission(String code) {
        List<PermissionEntity> entities = permissionDao.findByPermissionCode(code);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return new Permission(entities.get(0));
    }
    
    @Override
    @Transactional
    public void removePermission(Permission permission) {
        Assert.notNull(permission, "permission must not be null");
        Long id = permission.getId();
        Optional<PermissionEntity> entity = permissionDao.findById(id);
        entity.ifPresent((item) -> {
            // todo 后续添加事件
            List<PermissionRoleEntity> entities = permissionRoleDao.findByPermissionEntityId(id);
            permissionRoleDao.deleteAll(entities);
            permissionRoleDao.flush();
            permissionDao.delete(item);
            permissionDao.flush();
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Role[] getAssignedRoles(Permission permission) {
        Assert.notNull(permission, "permission must not be null");
        Optional<PermissionEntity> entity = permissionDao.findById(permission.getId());
        return entity.map((item) -> {
            List<PermissionRoleEntity> entities = permissionRoleDao.findByPermissionEntityId(item.getId());
            Role[] roles = new Role[entities.size()];
            for (int i = 0; i < entities.size(); i++) {
                PermissionRoleEntity permissionRole = entities.get(i);
                roles[i] = new Role(roleDao.getById(permissionRole.getRoleId()));
            }
            return roles;
        }).orElseGet(() -> {
            logger.warn("permission \"{}\" not found!", permission.getId());
            return new Role[0];
        });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Permission[] getAssignedPermissions(Role role) {
        Assert.notNull(role, "role must not be null");
        List<PermissionRoleEntity> entities = permissionRoleDao.findByRoleId(role.getId());
        Permission[] permissions = new Permission[entities.size()];
        for (int i = 0; i < entities.size(); i++) {
            permissions[i] = new Permission(entities.get(i).getPermissionEntity());
        }
        return permissions;
    }
    
    @Override
    @Transactional
    public void assignRole(Permission permission, Role[] roles) {
        Assert.notNull(permission, "permission must not be null");
        Assert.notNull(roles, "roles must not be null");
        PermissionEntity permissionEntity = permissionDao.findById(permission.getId()).<IllegalArgumentException>orElseThrow(() ->{
            logger.error("permission {} not found!", permission.getId());
            throw new IllegalArgumentException("permission " + permission.getId() + " not found!");
        });
        Iterator<PermissionRoleEntity> iterator = permissionRoleDao.findByPermissionEntityId(permissionEntity.getId()).iterator();
        List<Long> included = new ArrayList<>();
        List<Long> grantIds = new ArrayList();
        while (iterator.hasNext()){
            included.add(iterator.next().getRoleId());
        }
        for (Role role : roles) {
            Long roleId = role.getId();
            if (!included.contains(roleId)) {
                included.add(roleId);
                PermissionRoleEntity entity = new PermissionRoleEntity();
                entity.setRoleId(roleId);
                entity.setPermissionEntity(permissionEntity);
                permissionRoleDao.persist(entity);
                grantIds.add(roleId);
            }
        }
        permissionRoleDao.flush();
        // todo 后续添加事件
        
    }
    
    @Override
    @Transactional
    public void deassignRole(Permission permission, Role[] roles) {
        Assert.notNull(permission, "permission must not be null");
        Assert.notNull(roles, "roles must not be null");
        List<Long> grantIds = new ArrayList();
        List<Long> revokeIds = new ArrayList();
        Long permissionId = permission.getId();
        for (Role role : roles) {
            Long roleId = role.getId();
            PermissionRoleEntity entity = permissionRoleDao.findByPermissionEntityIdAndRoleId(permissionId, roleId);
            if(entity != null){
                revokeIds.add(roleId);
                permissionRoleDao.delete(entity);
            }
        }
        permissionRoleDao.flush();
        // todo 后续添加事件
    }
    
    @Override
    @Transactional
    public void reassignRole(Permission permission, Role[] roles) {
        Assert.notNull(permission, "permission must not be null");
        Assert.notNull(roles, "roles must not be null");
        Long permissionId = permission.getId();
        PermissionEntity permissionEntity = permissionDao.findById(permission.getId()).<IllegalArgumentException>orElseThrow(() ->{
            logger.error("permission {} not found!", permission.getId());
            throw new IllegalArgumentException("permission " + permission.getId() + " not found!");
        });
        Map<Long, PermissionRoleEntity> toBeDeleted = new LinkedHashMap();
        Iterator<PermissionRoleEntity> iterator = permissionRoleDao.findByPermissionEntityId(permissionId).iterator();
        while(iterator.hasNext()) {
            PermissionRoleEntity next = iterator.next();
            toBeDeleted.put(next.getRoleId(), next);
        }
        List<Long> grantIds = new ArrayList();
        List<Long> revokeIds = new ArrayList();
        Map<Long, PermissionRoleEntity> included = new LinkedHashMap();
    
        for (Role role : roles) {
            Long roleId = role.getId();
            if(!included.containsKey(roleId)){
                PermissionRoleEntity remove = toBeDeleted.remove(roleId);
                if(remove == null){
                    remove = new PermissionRoleEntity();
                    remove.setRoleId(roleId);
                    remove.setPermissionEntity(permissionEntity);
                    remove = permissionRoleDao.merge(remove);
                    grantIds.add(roleId);
                }
                included.put(roleId, remove);
            }
        }
        Iterator<Map.Entry<Long, PermissionRoleEntity>> entryIterator = toBeDeleted.entrySet().iterator();
        while(entryIterator.hasNext()) {
            Map.Entry<Long, PermissionRoleEntity> next = entryIterator.next();
            PermissionRoleEntity entity = next.getValue();
            revokeIds.add(entity.getRoleId());
            permissionRoleDao.delete(entity);
        }
        permissionRoleDao.flush();
        // todo 后续添加事件
    }
    
    @Override
    @Transactional
    public void reassignPermission(Role role, Permission[] Permissions) {
        Assert.notNull(role, "role must not be null");
        Long roleId = role.getId();
        if(roleDao.findById(roleId).orElse(null) == null){
            logger.error("role {} not found!", roleId);
            throw new IllegalArgumentException("role " + roleId + " not found!");
        }else{
            Map<Long, PermissionRoleEntity> toBeDeleted = new LinkedHashMap();
            Iterator<PermissionRoleEntity> iterator = permissionRoleDao.findByRoleId(roleId).iterator();
            while (iterator.hasNext()){
                PermissionRoleEntity next = iterator.next();
                toBeDeleted.put(next.getPermissionEntity().getId(), next);
            }
    
            List<Permission> grants = new ArrayList();
            List<Permission> revokes = new ArrayList();
            Map<Long, PermissionRoleEntity> included = new LinkedHashMap();
            
            if(Permissions != null){
                for (Permission permission : Permissions) {
                    Long permissionId = permission.getId();
                    if(!included.containsKey(permissionId)){
                        PermissionRoleEntity entity = toBeDeleted.remove(permissionId);
                        if(entity == null){
                            entity = new PermissionRoleEntity();
                            entity.setRoleId(roleId);
                            PermissionEntity permissionEntity = permissionDao.findById(permissionId).orElse(null);
                            if(permissionEntity == null){
                                logger.warn("permission {} not found!", permissionId);
                                continue;
                            }
    
                            entity.setPermissionEntity(permissionEntity);
                            entity = permissionRoleDao.merge(entity);
                            grants.add(new Permission(entity.getPermissionEntity()));
                        }
                        included.put(permissionId, entity);
                    }
                }
            }
            Iterator<Map.Entry<Long, PermissionRoleEntity>> entryIterator = toBeDeleted.entrySet().iterator();
            while(entryIterator.hasNext()) {
                Map.Entry<Long, PermissionRoleEntity> entry = entryIterator.next();
                PermissionRoleEntity entity = entry.getValue();
                revokes.add(new Permission(entity.getPermissionEntity()));
                permissionRoleDao.delete(entity);
            }
            permissionRoleDao.flush();
            // todo 后续添加事件
        }
    }
}
