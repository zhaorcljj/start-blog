package com.startblog.sys.permission.entity;

import com.startblog.sys.permission.bean.Permission;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限实体类
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Entity(name = "com.startblog.sys.permission.entity.PermissionEntity")
@Table(name = "PERMISSION", schema = "startblog")
public class PermissionEntity implements Serializable {
    
    private static final long serialVersionUID = 1473315275875160518L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "permissionEntityGenerator")
    @TableGenerator(name = "permissionEntityGenerator",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "PERMISSION",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "PERMISSION_CODE", nullable = false)
    private String permissionCode;
    
    @Column(name = "PERMISSION_NAME", nullable = false)
    private String permissionName;
    
    @Column(name = "PERMISSION_DESCRIPTION")
    private String permissionDescription;
    
    public String getPermissionName() {
        return permissionName;
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
    
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    
    public String getPermissionDescription() {
        return permissionDescription;
    }
    
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
    
    public PermissionEntity(Long id, String permissionCode, String permissionName, String permissionDescription) {
        this.id = id;
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
    }
    
    public PermissionEntity(Permission bean) {
        this.id = bean.getId();
        this.permissionCode = bean.getPermissionCode();
        this.permissionName = bean.getPermissionName();
        this.permissionDescription = bean.getPermissionDescription();
    }
    
    public PermissionEntity() {
    }
    
    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", permissionCode='" + permissionCode + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                '}';
    }
}