package com.startblog.security.user.service;

import com.startblog.sys.role.bean.Role;
import com.startblog.sys.role.bean.RoleUserRelation;
import com.startblog.sys.user.bean.User;

import java.util.List;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
public interface RoleInfoService {
    /**
     * 创建角色
     * @param role 待创建的角色
     * @return
     */
    Role createRole(Role role);
    
    /**
     * 修改角色
     * @param role 待修改的角色
     * @return
     */
    Role updateRole(Role role);
    /**
     * 查询角色对象，如果没有则返回null
     * @param id 待查询角色的id
     * @return
     */
    Role getRoleById(Long id);
    
    /**
     * 查询角色对象，如果没有则返回null
     * @param code 待查询角色的code
     * @return
     */
    Role getRole(String code);
    
    /**
     * 删除角色
     * @param role 待删除的角色
     * @return
     */
    void removeRole(Role role);
    
    /**
     * 返回具有指定角色的用户集合
     * @param role 待查询的角色
     * @return 具有角色role的用户集合
     */
    public User[] getAssignedUsers(Role role);
    
    /**
     * 返回指定用户所拥有的角色集合
     * @param user 待查询的用户
     * @return 用户所具有的角色集合
     */
    public Role[] getAssignedRoles(User user);
    
    /**
     * 将用户设置为角色成员(设定users具有角色role)
     * @param role  所授予的角色
     * @param users 待授予角色的用户集合
     */
    public void assignUser(Role role, User[] users);
    
    /**
     * 将用户从角色成员中去除(使users不再具有角色role)
     * @param role  待移除的角色
     * @param users 将要被移除角色的用户集合
     */
    public void deassignUser(Role role, User[] users);
    
    /**
     * 重新设置角色的用户成员(重置以确保只有users具有role角色)
     * @param role  待授予的角色
     * @param users 待授予角色的用户集合
     */
    public void reassignUser(Role role, User[] users);
    
    /**
     * 重新设置用户所具有的角色(重置以确保用户只具有roles角色)
     * @param user  待重置的用户
     * @param roles 重置后用户所拥有的角色集合
     */
    public void reassignRole(User user, Role[] roles);
}
