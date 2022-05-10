package com.startblog.user.dao;

import com.startblog.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityDao extends JpaRepository<UserEntity, Long>{
    UserEntity getByAccount(String account);
}