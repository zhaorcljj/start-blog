package com.startblog.sys.role.dao;

import com.startblog.data.jpa.GenericRepository;
import com.startblog.sys.role.entity.RoleUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色用户关系dao
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Repository("roleUserDao")
public interface RoleUserDao extends GenericRepository<RoleUserEntity, Long> {
    
    List<RoleUserEntity> findByRoleEntityId(Long roleId);
    
    List<RoleUserEntity> findByUserId(Long userId);
    
    RoleUserEntity findByRoleEntityIdAndUserId(Long roleId, Long userId);
}