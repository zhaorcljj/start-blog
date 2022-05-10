package com.startblog.user.service.impl;

import com.startblog.user.bean.User;
import com.startblog.user.dao.UserEntityDao;
import com.startblog.user.entity.UserDetailEntity;
import com.startblog.user.entity.UserEntity;
import com.startblog.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        entity.setCreator("admin");
        entity.setCreationTime(new Date());
        entity.setLastModifyTime(new Date());
        entity.setDeleted(0);
        entity.setAccount(user.getAccount());
        entity.setName(user.getName());
        entity.setPassWord(user.getPassWord());
        entity.setStatus(1);
        UserDetailEntity userDetail = new UserDetailEntity();
        userDetail.setPhone(user.getPhone());
        userDetail.setMail(user.getMail());
        userDetail.setBirthday(user.getBirthday());
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
