package com.startblog.sys.role.bean;

import com.startblog.sys.role.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 角色bean
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Component
public class Role implements Serializable {
    private static final long serialVersionUID = 1133127698486633611L;
    private Long id;
    private String roleCode;
    private String roleName;
    private String roleDescription;
    
    public Role() {
    }
    
    public Role(Long id, String roleCode, String roleName, String roleDescription) {
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
    public Role(RoleEntity entity) {
        this.id = entity.getId();
        this.roleCode = entity.getRoleCode();
        this.roleName = entity.getRoleName();
        this.roleDescription = entity.getRoleDescription();
    }
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
    
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
