package com.startblog.sys.permission.bean;

import com.startblog.sys.permission.entity.PermissionEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 权限bean
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Component
public class Permission implements Serializable {
    
    private static final long serialVersionUID = -6289768763057529619L;
    
    private Long id;
    private String permissionCode;
    private String permissionName;
    private String permissionDescription;
    
    public Permission() {
    }
    
    public Permission(Long id, String permissionCode, String permissionName, String permissionDescription) {
        this.id = id;
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
    }
    
    public Permission(PermissionEntity entity) {
        this.id = entity.getId();
        this.permissionCode = entity.getPermissionCode();
        this.permissionName = entity.getPermissionName();
        this.permissionDescription = entity.getPermissionDescription();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPermissionCode() {
        return permissionCode;
    }
    
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
    
    public String getPermissionName() {
        return permissionName;
    }
    
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    
    public String getPermissionDescription() {
        return permissionDescription;
    }
    
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
    
    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionCode='" + permissionCode + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                '}';
    }
}
