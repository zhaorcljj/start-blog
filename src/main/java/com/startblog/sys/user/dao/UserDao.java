package com.startblog.sys.user.dao;

import com.startblog.data.jpa.GenericRepository;
import com.startblog.sys.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
@Repository("userDao")
public interface UserDao extends GenericRepository<UserEntity, Long> {
//public interface UserDao extends JpaSpecificationExecutor<UserEntity>, JpaRepository<UserEntity, Long> {
    List<UserEntity> getByAccount(String account);
}