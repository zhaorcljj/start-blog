package com.startblog.sys.permission.service;

import com.startblog.sys.permission.bean.Permission;
import com.startblog.sys.role.bean.Role;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
public interface PermissionInfoManager {
    /**
     * 创建权限信息
     * @param permission
     * @return
     */
    Permission create(Permission permission);
    
    /**
     * 修改权限信息
     * @param permission
     * @return
     */
    Permission update(Permission permission);
    
    /**
     * 根据ID获取权限信息
     * @param id
     * @return
     */
    Permission getPermissionById(Long id);
    
    /**
     * 根据code获取权限信息
     * @param code
     * @return
     */
    Permission getPermission(String code);
    
    /**
     * 删除权限信息
     * @param permission
     * @return
     */
    void removePermission(Permission permission);
    
    /**
     * 返回具有指定权限的角色集合
     * @param permission 待查询的权限
     * @return 具有权限permission的角色集合
     */
    Role[] getAssignedRoles(Permission permission);
    
    /**
     * 返回指定角色拥有的权限集合
     * @param role 待查询的角色
     * @return 角色所具有的权限集合
     */
    Permission[] getAssignedPermissions(Role role);
    
    /**
     * 授权permission给角色roles
     * @param permission 所授的权限
     * @param roles 待授权的角色集合
     */
    void assignRole(Permission permission, Role[] roles);
    
    /**
     * 撤销角色集合roles的权限permission
     * @param permission
     * @param roles
     */
    void deassignRole(Permission permission, Role[] roles);
    
    /**
     * 重新授权permission给角色集合roles(重置以确保只有roles拥有权限permission)
     * @param permission 待授权限
     * @param roles 待授权角色
     */
    void reassignRole(Permission permission, Role[] roles);
    
    /**
     * 重置角色role的权限(重置以确保role只拥有权限Permissions)
     * Permissions为null时 角色role将不具备任何权限
     * @param role 待授权角色
     * @param Permissions 待授权限
     */
    void reassignPermission(Role role, Permission[] Permissions);
}
