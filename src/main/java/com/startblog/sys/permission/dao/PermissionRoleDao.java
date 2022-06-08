package com.startblog.sys.permission.dao;

import com.startblog.data.jpa.GenericRepository;
import com.startblog.sys.permission.entity.PermissionRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限角色对应dao
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Repository("permissionRoleDao")
public interface PermissionRoleDao extends GenericRepository<PermissionRoleEntity, Long> {
    List<PermissionRoleEntity> findByPermissionEntityId(Long permissionId);
    List<PermissionRoleEntity> findByRoleId(Long roleId);
    PermissionRoleEntity findByPermissionEntityIdAndRoleId(Long permissionId, Long roleId);
    
}