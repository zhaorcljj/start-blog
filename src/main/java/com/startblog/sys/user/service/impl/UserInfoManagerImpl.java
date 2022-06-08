package com.startblog.sys.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.startblog.sys.user.bean.User;
import com.startblog.sys.user.dao.UserDao;
import com.startblog.sys.user.entity.UserDateEntity;
import com.startblog.sys.user.entity.UserEntity;
import com.startblog.sys.user.enums.UserStatus;
import com.startblog.sys.user.service.UserInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 用户服务
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
@Service(value = "userInfoManager")
public class UserInfoManagerImpl implements UserInfoManager, InitializingBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userDao, "userDao is required!");
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        Optional<UserEntity> entity = userDao.findById(id);
        return entity.map((item) -> new User(item)).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User getUserByAccount(String account) {
        List<UserEntity> entities = userDao.getByAccount(account);
        if(CollectionUtils.isEmpty(entities)){
            return null;
        }
        return new User(entities.get(0));
    }
    
    @Override
    @Transactional
    public User updateUser(User user) {
        Assert.hasText(user.getAccount(),"用户Account为空！");
        
        List<UserEntity> entities = userDao.getByAccount(user.getAccount());
        
        if(!CollectionUtils.isEmpty(entities)){
            UserEntity entity = entities.get(0);
            // entity.setAccount(user.getAccount());
            entity.setName(user.getName());
            entity.setPassWord(passwordEncoder.encode(user.getPassWord()));
            entity.setEmail(user.getEmail());
            entity.setTelephone(user.getTelephone());
            UserDateEntity userDate = entity.getData();
            JSONObject props = user.getProps();
            if(props != null){
                userDate.setDetail(props.toJSONString());
            }
            userDate.setUser(entity);
            entity.setData(userDate);
            userDao.saveAndFlush(entity);
            return user;
        }else{
            throw new NullPointerException("该用户不存在！");
        }
    }
    
    @Override
    @Transactional
    public User createUser(User user) {
        UserEntity entity = new UserEntity();
        entity.setCreatorAccount(user.getAccount());
        entity.setCreationTime(System.currentTimeMillis());
        entity.setLastModifyTime(System.currentTimeMillis());
        entity.setAccount(user.getAccount());
        entity.setName(user.getName());
        entity.setPassWord(passwordEncoder.encode(user.getPassWord()));
        entity.setStatus(UserStatus.NORMAL.getIndex());
        entity.setEmail(user.getEmail());
        entity.setTelephone(user.getTelephone());
        entity.setEnabled(1);
        UserDateEntity userDate = new UserDateEntity();
        JSONObject props = user.getProps();
        if(props != null){
            userDate.setDetail(props.toJSONString());
        }
        userDate.setUser(entity);
        entity.setData(userDate);
        entity = userDao.mergeAndFlush(entity);
        return new User(entity);
    }
    
}
