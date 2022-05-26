package com.startblog.user.entity;

import com.startblog.common.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月23日
 */
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 8522476643979584381L;
    
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
    private String Email;
    /**
     * 电话
     */
    @Column(name = "TELEPHONE", length = 50)
    private String Telephone;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private int status;
    /**
     * 最后登录时间
     */
    @Column(name = "LAST_LOGIN_TIME")
    private Long lastLoginTime;
    
    @OneToOne(mappedBy = "user",cascade = {CascadeType.ALL},
    targetEntity = UserDetailEntity.class)
    private UserDetailEntity userDetailEntity;
    
    public UserEntity(Long id, String account, String passWord,
                      String name, String email, String telephone,
                      int status, Long lastLoginTime,
                      UserDetailEntity userDetailEntity) {
        super();
        this.id = id;
        this.account = account;
        this.passWord = passWord;
        this.name = name;
        this.Email = email;
        this.Telephone = telephone;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.userDetailEntity = userDetailEntity;
    }
    
    public UserEntity() {
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
    
    public Long getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public UserDetailEntity getUserDetailEntity() {
        return userDetailEntity;
    }
    
    public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
    }
}
