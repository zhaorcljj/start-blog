package com.startblog.security.user.service.impl;

import com.startblog.security.user.service.RoleInfoService;
import com.startblog.sys.role.bean.Role;
import com.startblog.sys.role.bean.RoleUserRelation;
import com.startblog.sys.role.service.RoleInfoManager;
import com.startblog.sys.user.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("roleInfoManager")
    private RoleInfoManager roleInfoManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(roleInfoManager, "roleInfoManager is required!");
    
    }
    
    @Override
    public Role createRole(Role role) {
        return roleInfoManager.createRole(role);
    }
    
    @Override
    public Role updateRole(Role role) {
        return roleInfoManager.updateRole(role);
    }
    
    @Override
    public Role getRoleById(Long id) {
        return roleInfoManager.getRoleById(id);
    }
    
    @Override
    public Role getRole(String code) {
        return roleInfoManager.getRole(code);
    }
    
    @Override
    public void removeRole(Role role) {
        roleInfoManager.removeRole(role);
    }
    
    @Override
    public User[] getAssignedUsers(Role role) {
        return roleInfoManager.getAssignedUsers(role);
    }
    
    @Override
    public Role[] getAssignedRoles(User user) {
        return roleInfoManager.getAssignedRoles(user);
    }
    
    @Override
    public void assignUser(Role role, User[] users) {
        roleInfoManager.assignUser(role, users);
    }
    
    @Override
    public void deassignUser(Role role, User[] users) {
        roleInfoManager.deassignUser(role, users);
    }
    
    @Override
    public void reassignUser(Role role, User[] users) {
        roleInfoManager.reassignUser(role, users);
    }
    
    @Override
    public void reassignRole(User user, Role[] roles) {
        roleInfoManager.reassignRole(user, roles);
    }
}
