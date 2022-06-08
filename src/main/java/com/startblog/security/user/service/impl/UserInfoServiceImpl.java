package com.startblog.security.user.service.impl;

import com.startblog.security.user.service.UserInfoService;
import com.startblog.sys.user.bean.User;
import com.startblog.sys.user.service.UserInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("userInfoManager")
    private UserInfoManager userInfoManager;
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userInfoManager, "userInfoManager is required!");
    }
    
    @Override
    public User getUserById(Long id) {
        return userInfoManager.getUserById(id);
    }
    
    @Override
    public User getUserByAccount(String account) {
        return userInfoManager.getUserByAccount(account);
    }
    
    @Override
    public User updateUser(User user) {
        return userInfoManager.updateUser(user);
    }
    
    @Override
    public User createUser(User user) {
        return userInfoManager.createUser(user);
    }
}
