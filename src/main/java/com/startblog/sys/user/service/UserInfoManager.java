package com.startblog.sys.user.service;

import com.startblog.sys.user.bean.User;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
public interface UserInfoManager {
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);
    
    /**
     * 根据account获取用户
     * @param account
     * @return
     */
    User getUserByAccount(String account);
    
    /**
     * 修改用户
     * @param user
     * @return
     */
    User updateUser(User user);
    
    /**
     * 新建用户
     * @param user
     * @return
     */
    User createUser(User user);
    
}
