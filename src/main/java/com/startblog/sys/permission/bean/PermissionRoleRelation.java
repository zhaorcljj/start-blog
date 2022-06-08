package com.startblog.sys.permission.bean;

import com.startblog.sys.permission.entity.PermissionRoleEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 权限角色关联bean
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Component
public class PermissionRoleRelation implements Serializable {
    private static final long serialVersionUID = -2627088284858415479L;
    private Long id;
    private Permission permission;
    private Long roleId;
    
    public PermissionRoleRelation() {
    }
    
    
    public PermissionRoleRelation(Long id, Permission permission, Long roleId) {
        this.id = id;
        this.permission = permission;
        this.roleId = roleId;
    }
    
    public PermissionRoleRelation(PermissionRoleEntity entity) {
        this.id = entity.getId();
        this.permission = new Permission(entity.getPermissionEntity());
        this.roleId = entity.getRoleId();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Permission getPermission() {
        return permission;
    }
    
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    @Override
    public String toString() {
        return "PermissionRoleRelation{" +
                "id=" + id +
                ", permissionId=" + permission.getId() +
                ", roleId=" + roleId +
                '}';
    }
}
