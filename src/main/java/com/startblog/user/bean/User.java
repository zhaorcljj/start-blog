package com.startblog.user.bean;

import com.startblog.user.entity.UserEntity;

import java.io.Serializable;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */

//@Component(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 6418003259714869839L;
    private String account;
    private String name;
    private String passWord;
    private String phone;
    private String mail;
    private String birthday;
    
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getPassWord() {
        return passWord;
    }
    
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public User(String account, String name, String phone, String mail, String birthday) {
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.birthday = birthday;
    }
    public User(UserEntity entity){
        this.account = entity.getAccount();
        this.name = entity.getName();
        this.phone = entity.getUserDetailEntity().getPhone();
        this.mail = entity.getUserDetailEntity().getMail();
        this.birthday = entity.getUserDetailEntity().getBirthday();
    }
}
