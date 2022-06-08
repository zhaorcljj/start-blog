package com.startblog.sys.role.entity;

import com.startblog.sys.role.bean.RoleUserRelation;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色用户关系类
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Entity(name = "com.startblog.sys.role.entity.RoleUserRelationEntity")
@Table(name = "ROLE_USER", schema = "startblog")
public class RoleUserEntity implements Serializable {
    
    private static final long serialVersionUID = -8653036500077920421L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "roleUserEntityGenerator")
    @TableGenerator(name = "roleUserEntityGenerator",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "ROLE_USER",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private RoleEntity roleEntity;
    
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public RoleEntity getRoleEntity() {
        return roleEntity;
    }
    
    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
    public RoleUserEntity(RoleUserRelation bean) {
        this.id = bean.getId();
        this.roleEntity = new RoleEntity(bean.getRole());
        this.userId = bean.getUserId();
    }
    
    public RoleUserEntity() {
    }
    
}