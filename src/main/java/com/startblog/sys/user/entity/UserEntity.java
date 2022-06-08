package com.startblog.sys.user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author zhaorc
 * @version 1.0, 2022年04月23日
 */
@Entity(name = "com.startblog.sys.user.entity.UserEntity")
@Table(name = "USER", schema = "startblog")
public class UserEntity implements Serializable {
    
    private static final long serialVersionUID = -5889705833827884080L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userEntityGenerator")
    @TableGenerator(name = "userEntityGenerator",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "USER",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    /**
     * 账号
     */
    @Column(name = "ACCOUNT", nullable = false, length = 50)
    private String account;
    /**
     * 密码
     */
    @Column(name = "PASS_WORD", nullable = false, length = 255)
    private String passWord;
    /**
     * 姓名
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    /**
     * 邮箱
     */
    @Column(name = "EMAIL", length = 50)
    private String email;
    /**
     * 电话
     */
    @Column(name = "TELEPHONE", length = 50)
    private String telephone;
    /**
     * 状态（0：正常，1：停用，2：注销，3：锁定）
     */
    @Column(name = "STATUS")
    private int status;
    
    /**
     * 是否启用（0：禁用，1：启用）
     */
    @Column(name = "ENABLED")
    private int enabled;
    /**
     * 最后登录时间
     */
    @Column(name = "LAST_LOGIN_TIME")
    private Long lastLoginTime;
    
    /**
     * 创建人id
     */
    @Column(name = "CREATOR_ID")
    private Long creatorId;
    /**
     * 创建人账号
     */
    @Column(name = "CREATOR_ACCOUNT")
    private String creatorAccount;
    /**
     * 创建人姓名
     */
    @Column(name = "CREATOR_NAME")
    private String creatorName;
    /**
     * 修改人id
     */
    @Column(name = "MODIFY_USER_ID")
    private Long modifyUserId;
    /**
     * 修改人账号
     */
    @Column(name = "MODIFY_USER_ACCOUNT")
    private String modifyUserAccount;
    /**
     * 修改人姓名
     */
    @Column(name = "MODIFY_USER_NAME")
    private String modifyUserName;
    /**
     * 创建时间
     */
    @Column(name = "CREATION_TIME", updatable = false)
    private Long creationTime;
    /**
     * 修改时间
     */
    @Column(name = "LAST_MODIFY_TIME")
    private Long lastModifyTime;
    
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL},
            targetEntity = UserDateEntity.class)
    private UserDateEntity data;
    
    public UserEntity() {
    }
    
    public UserEntity(Long id, String account, String passWord, String name, String email,
                      String telephone, int status, int enabled, Long lastLoginTime,
                      Long creatorId, String creatorAccount, String creatorName, Long modifyUserId,
                      String modifyUserAccount, String modifyUserName, Long creationTime,
                      Long lastModifyTime, UserDateEntity data) {
        this.id = id;
        this.account = account;
        this.passWord = passWord;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
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
        this.data = data;
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
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    
    public UserDateEntity getData() {
        return data;
    }
    
    public void setData(UserDateEntity data) {
        this.data = data;
    }
}
