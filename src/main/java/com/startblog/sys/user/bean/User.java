package com.startblog.sys.user.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.startblog.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户bean
 * @author zhaorc
 * @version 1.0, 2022年04月30日
 */

@Component(value = "user")
public class User implements Serializable {
    
    private static final long serialVersionUID = 6418003259714869839L;
    
    private Long id;
    private String account;
    private String passWord;
    private String name;
    private String Email;
    private String Telephone;
    private int status;
    private int enabled;
    private Long lastLoginTime;
    private Long creatorId;
    private String creatorAccount;
    private String creatorName;
    private Long modifyUserId;
    private String modifyUserAccount;
    private String modifyUserName;
    private Long creationTime;
    private Long lastModifyTime;
    private JSONObject props;
    
    public User() {
    }
    
    public User(Long id, String account, String passWord, String name, String email,
                String telephone, int status, int enabled, Long lastLoginTime, Long creatorId,
                String creatorAccount, String creatorName, Long modifyUserId, String modifyUserAccount,
                String modifyUserName, Long creationTime, Long lastModifyTime, JSONObject props) {
        this.id = id;
        this.account = account;
        this.passWord = passWord;
        this.name = name;
        this.Email = email;
        this.Telephone = telephone;
        this.status = status;
        this.enabled = enabled;
        this.lastLoginTime = lastLoginTime;
        this.creatorId = creatorId;
        this.creatorAccount = creatorAccount;
        this.creatorName = creatorName;
        this.modifyUserId = modifyUserId;
        this.modifyUserAccount = modifyUserAccount;
        this.modifyUserName = modifyUserName;
        this.creationTime = creationTime;
        this.lastModifyTime = lastModifyTime;
        this.props = props;
    }
    
    public User(UserEntity entity){
        this.id = entity.getId();
        this.account = entity.getAccount();
        this.passWord = entity.getPassWord();
        this.name = entity.getName();
        this.Email = entity.getEmail();
        this.Telephone = entity.getTelephone();
        this.status = entity.getStatus();
        this.enabled = entity.getEnabled();
        this.lastLoginTime = entity.getLastLoginTime();
        this.creatorId = entity.getCreatorId();
        this.creatorAccount = entity.getCreatorAccount();
        this.creatorName = entity.getCreatorName();
        this.modifyUserId = entity.getModifyUserId();
        this.modifyUserAccount = entity.getModifyUserAccount();
        this.modifyUserName = entity.getModifyUserName();
        this.creationTime = entity.getCreationTime();
        this.lastModifyTime = entity.getLastModifyTime();
        
        String detail = entity.getData().getDetail();
        if(StringUtils.isNotBlank(detail)){
            this.props = JSON.parseObject(detail);
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getEnabled() {
        return enabled;
    }
    
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
    
    public Long getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public Long getCreatorId() {
        return creatorId;
    }
    
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    
    public String getCreatorAccount() {
        return creatorAccount;
    }
    
    public void setCreatorAccount(String creatorAccount) {
        this.creatorAccount = creatorAccount;
    }
    
    public String getCreatorName() {
        return creatorName;
    }
    
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    
    public Long getModifyUserId() {
        return modifyUserId;
    }
    
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
    
    public String getModifyUserAccount() {
        return modifyUserAccount;
    }
    
    public void setModifyUserAccount(String modifyUserAccount) {
        this.modifyUserAccount = modifyUserAccount;
    }
    
    public String getModifyUserName() {
        return modifyUserName;
    }
    
    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }
    
    public Long getCreationTime() {
        return creationTime;
    }
    
    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }
    
    public Long getLastModifyTime() {
        return lastModifyTime;
    }
    
    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
    
    public JSONObject getProps() {
        return props;
    }
    
    public void setProps(JSONObject props) {
        this.props = props;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                ", name='" + name + '\'' +
                ", Email='" + Email + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", status=" + status +
                ", enabled=" + enabled +
                ", lastLoginTime=" + lastLoginTime +
                ", creatorId=" + creatorId +
                ", creatorAccount='" + creatorAccount + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", modifyUserId=" + modifyUserId +
                ", modifyUserAccount='" + modifyUserAccount + '\'' +
                ", modifyUserName='" + modifyUserName + '\'' +
                ", creationTime=" + creationTime +
                ", lastModifyTime=" + lastModifyTime +
                ", props=" + props +
                '}';
    }
}
