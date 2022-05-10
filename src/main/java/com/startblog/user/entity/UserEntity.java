package com.startblog.user.entity;

import com.startblog.entity.BaseEntity;

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
    @Column(name = "ACCOUNT", nullable = false, updatable = false)
    private String account;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PASS_WORD", nullable = false)
    private String passWord;
    @Column(name = "STATUS")
    private int status;
    
    @OneToOne(mappedBy = "user",cascade = {CascadeType.ALL})
    private UserDetailEntity userDetailEntity;
    
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
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public UserDetailEntity getUserDetailEntity() {
        return userDetailEntity;
    }
    
    public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
    }
    
    public UserEntity() {
        super();
    }
    
    public UserEntity(Long id, String account, String name, String passWord, int status, UserDetailEntity userDetailEntity) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.passWord = passWord;
        this.status = status;
        this.userDetailEntity = userDetailEntity;
    }
}
