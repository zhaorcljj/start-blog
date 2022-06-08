package com.startblog.security.user.service.impl;

import com.startblog.security.user.service.PermissionInfoService;
import com.startblog.sys.permission.bean.Permission;
import com.startblog.sys.permission.service.PermissionInfoManager;
import com.startblog.sys.role.bean.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
@Service("permissionInfoService")
public class PermissionInfoServiceImpl implements PermissionInfoService, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("permissionInfoManager")
    private PermissionInfoManager permissionInfoManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(permissionInfoManager, "permissionInfoManager is required!");
    }
    
    
    @Override
    public Permission create(Permission permission) {
        return permissionInfoManager.create(permission);
    }
    
    @Override
    public Permission update(Permission permission) {
        return permissionInfoManager.update(permission);
    }
    
    @Override
    public Permission getPermissionById(Long id) {
        return permissionInfoManager.getPermissionById(id);
    }
    
    @Override
    public Permission getPermission(String code) {
        return permissionInfoManager.getPermission(code);
    }
    
    @Override
    public void removePermission(Permission permission) {
        permissionInfoManager.removePermission(permission);
    }
    
    @Override
    public Role[] getAssignedRoles(Permission permission) {
        return permissionInfoManager.getAssignedRoles(permission);
    }
    
    @Override
    public Permission[] getAssignedPermissions(Role role) {
        return permissionInfoManager.getAssignedPermissions(role);
    }
    
    @Override
    public void assignRole(Permission permission, Role[] roles) {
        permissionInfoManager.assignRole(permission, roles);
    }
    
    @Override
    public void deassignRole(Permission permission, Role[] roles) {
        permissionInfoManager.deassignRole(permission, roles);
    }
    
    @Override
    public void reassignRole(Permission permission, Role[] roles) {
        permissionInfoManager.reassignRole(permission, roles);
    }
    
    @Override
    public void reassignPermission(Role role, Permission[] Permissions) {
        permissionInfoManager.reassignPermission(role, Permissions);
    }
}
