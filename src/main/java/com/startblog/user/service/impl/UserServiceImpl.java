package com.startblog.user.service.impl;

import com.startblog.user.bean.User;
import com.startblog.user.dao.UserEntityDao;
import com.startblog.user.entity.UserDetailEntity;
import com.startblog.user.entity.UserEntity;
import com.startblog.user.enums.UserStatus;
import com.startblog.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService, InitializingBean {
    @Autowired
    private UserEntityDao userDao;
    
    public UserEntityDao getUserDao() {
        return userDao;
    }
    
    public void setUserDao(UserEntityDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
    
    }
    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userDao.findById(id).orElse(null);
        if(userEntity == null){
            return null;
        }
        return new User(userEntity);
    }
    
    @Override
    public User getUserByAccount(String account) {
        UserEntity entity = userDao.getByAccount(account);
        if(entity == null){
            return null;
        }
        return new User(entity);
    }
    
    @Override
    public com.startblog.security.user.User getUerByAccount(String account) {
        UserEntity entity = userDao.getByAccount(account);
        if(entity == null){
            return null;
        }
        return new com.startblog.security.user.User(entity.getAccount(), entity.getName());
    }
    
    @Override
    public User updateUser(User user) {
       
        
        return null;
    }
    
    @Override
    public User createUser(User user) {
        UserEntity entity = new UserEntity();
        entity.setCreatorAccount(user.getAccount());
        entity.setCreationTime(System.currentTimeMillis());
        entity.setLastModifyTime(System.currentTimeMillis());
        entity.setDeleted(0);
        entity.setAccount(user.getAccount());
        entity.setName(user.getName());
        entity.setPassWord(user.getPassWord());
        entity.setStatus(UserStatus.NORMAL.getIndex());
        entity.setEmail(user.getEmail());
        entity.setTelephone(user.getTelephone());
        UserDetailEntity userDetail = new UserDetailEntity();
        entity.setUserDetailEntity(userDetail);
        userDao.saveAndFlush(entity);
        return null;
    }
    
    @Override
    public boolean login(com.startblog.security.user.User user) {
        UserEntity entity = userDao.getByAccount(user.getAccount());
        if(entity == null){
            return false;
        }
        if (entity.getPassWord().equals(user.getPassWord())) {
            return true;
        }
        return false;
    }
    
    
}
