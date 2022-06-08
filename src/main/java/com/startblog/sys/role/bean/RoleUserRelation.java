package com.startblog.sys.role.bean;

import com.startblog.sys.role.entity.RoleUserEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 角色用户关系bean
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Component
public class RoleUserRelation implements Serializable {
    private static final long serialVersionUID = -5052430480500499567L;
    private Long id;
    private Role role;
    private Long userId;
    
    public RoleUserRelation() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public RoleUserRelation(RoleUserEntity entity) {
        this.id = entity.getId();
        this.role = new Role(entity.getRoleEntity());
        this.userId = entity.getUserId();
    }


    
    
}
