package com.startblog.user.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.startblog.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */

@Component(value = "userBean")
public class User implements Serializable {
    private static final long serialVersionUID = 6418003259714869839L;
    private String account;
    private String passWord;
    private String name;
    private String Email;
    private String Telephone;
    private Long lastLoginTime;
    private JSONObject props;
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getPassWord() {
        return passWord;
    }
    
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String email) {
        Email = email;
    }
    
    public String getTelephone() {
        return Telephone;
    }
    
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
    
    public Long getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public JSONObject getProps() {
        return props;
    }
    
    public void setProps(JSONObject props) {
        this.props = props;
    }
    
    public User() {
    }
    
    public User(String account, String passWord, String name,
                String email, String telephone, Long lastLoginTime,
                JSONObject props) {
        this.account = account;
        this.passWord = passWord;
        this.name = name;
        this.Email = email;
        this.Telephone = telephone;
        this.lastLoginTime = lastLoginTime;
        this.props = props;
    }
    
    public User(UserEntity entity){
        this.account = entity.getAccount();
        this.name = entity.getName();
        this.Email = entity.getEmail();
        this.Telephone = entity.getTelephone();
        this.lastLoginTime = entity.getLastLoginTime();
        String detail = entity.getUserDetailEntity().getDetail();
        if(StringUtils.isNotBlank(detail)){
            this.props = JSON.parseObject(detail);
        }
    }
}
