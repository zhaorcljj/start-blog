package com.startblog.security.user;

import java.io.Serializable;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */
public class User implements Serializable {
    private static final long serialVersionUID = -4483064376629352324L;
    private String account;
    private String name;
    private String passWord;
    
    public User(String account, String name, String passWord) {
        this.account = account;
        this.name = name;
        this.passWord = passWord;
    }
    
    public User(String account, String name) {
        this.account = account;
        this.name = name;
    }
    
    public User() {
    }
    
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
    
    public String getPassWord() {
        return passWord;
    }
    
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
