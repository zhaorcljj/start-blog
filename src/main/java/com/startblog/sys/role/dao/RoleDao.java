package com.startblog.sys.role.dao;

import com.startblog.data.jpa.GenericRepository;
import com.startblog.sys.role.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
@Repository("roleDao")
public interface RoleDao extends GenericRepository<RoleEntity, Long> {
    List<RoleEntity> findByRoleCode(String roleCode);
}