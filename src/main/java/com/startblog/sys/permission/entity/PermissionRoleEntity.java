package com.startblog.sys.permission.entity;

import com.startblog.sys.permission.bean.PermissionRoleRelation;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限角色关系类
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Entity(name = "com.startblog.sys.permission.entity.PermissionRoleEntity")
@Table(name = "PERMISSION_ROLE", schema = "startblog")
public class PermissionRoleEntity implements Serializable {
    
    private static final long serialVersionUID = -8236574240916631061L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "permissionRoleEntity")
    @TableGenerator(name = "permissionRoleEntity",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "PERMISSION_ROLE",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name= "PERMISSION_ID", nullable = false)
    private PermissionEntity permissionEntity;
    
    @Column(name= "ROLE_ID", nullable = false)
    private Long roleId;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public PermissionEntity getPermissionEntity() {
        return permissionEntity;
    }
    
    public void setPermissionEntity(PermissionEntity permissionEntity) {
        this.permissionEntity = permissionEntity;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public PermissionRoleEntity(Long id, PermissionEntity permissionEntity, Long roleId) {
        this.id = id;
        this.permissionEntity = permissionEntity;
        this.roleId = roleId;
    }
    
    public PermissionRoleEntity() {
    }
    
    public PermissionRoleEntity(PermissionRoleRelation bean) {
        this.id = bean.getId();
        this.permissionEntity = new PermissionEntity(bean.getPermission());
        this.roleId = bean.getRoleId();
    }
    
    @Override
    public String toString() {
        return "PermissionRoleEntity{" +
                "id=" + id +
                ", permissionEntityId=" + permissionEntity.getId() +
                ", roleId=" + roleId +
                '}';
    }
}
