package com.startblog.sys.permission.dao;

import com.startblog.data.jpa.GenericRepository;
import com.startblog.sys.permission.entity.PermissionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限dao
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Repository("permissionDao")
public interface PermissionDao extends GenericRepository<PermissionEntity, Long> {
    List<PermissionEntity> findByPermissionCode(String permissionCode);
}