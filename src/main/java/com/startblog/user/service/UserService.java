package com.startblog.user.service;

import com.startblog.user.bean.User;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
public interface UserService {
    User getUserById(Long id);
    User getUserByAccount(String account);
    com.startblog.security.user.User getUerByAccount(String account);
    User updateUser(User user);
    User createUser(User User);
    boolean login(com.startblog.security.user.User user);
}
