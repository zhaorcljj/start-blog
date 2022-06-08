package com.startblog.sys.role.entity;

import com.startblog.sys.role.bean.Role;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色类
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Entity(name = "com.startblog.sys.role.entity.RoleEntity")
@Table(name = "ROLE", schema = "startblog")
public class RoleEntity implements Serializable {
    
    private static final long serialVersionUID = 8457118607201698731L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "roleEntityGenerator")
    @TableGenerator(name = "roleEntityGenerator",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "ROLE",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "ROLE_CODE")
    private String roleCode;
    
    @Column(name = "ROLE_NAME")
    private String roleName;
    
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleDescription() {
        return roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    
    public RoleEntity() {
    }
    
    public RoleEntity(Long id, String roleCode, String roleName, String roleDescription) {
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
    
    public RoleEntity(Role bean) {
        this.id = bean.getId();
        this.roleCode = bean.getRoleCode();
        this.roleName = bean.getRoleName();
        this.roleDescription = bean.getRoleDescription();
    }
    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}