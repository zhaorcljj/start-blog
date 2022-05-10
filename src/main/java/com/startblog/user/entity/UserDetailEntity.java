package com.startblog.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_DETAIL")
public class UserDetailEntity implements Serializable {
    private static final long serialVersionUID = 8080002497344782661L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userDetailEntityGenerator")
    @TableGenerator(name = "userDetailEntityGenerator",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "USER_DETAIL",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "MAIL")
    private String mail;
    
    @Column(name = "BIRTHDAY")
    private String birthday;
    
    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private UserEntity user;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    public UserDetailEntity(Long id, String phone, String mail, String birthday, UserEntity user) {
        this.id = id;
        this.phone = phone;
        this.mail = mail;
        this.birthday = birthday;
        this.user = user;
    }
    
    public UserDetailEntity() {
        super();
    }
}