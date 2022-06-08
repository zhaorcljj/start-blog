package com.startblog.sys.user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户扩展类
 * @author zhaorc
 * @version 1.0, 2022年04月23日
 */
@Entity(name = "com.startblog.sys.user.entity.UserDateEntity")
@Table(name = "USER_DATE", schema = "startblog")
public class UserDateEntity implements Serializable {
    private static final long serialVersionUID = 8080002497344782661L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userDateEntityGenerator")
    @TableGenerator(name = "userDateEntityGenerator",
            table = "TABLE_ID_GEN",
            pkColumnName = "GEN_NAME",
            pkColumnValue = "USER_DATE",
            valueColumnName = "GEN_VAL",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;
    /**
     * 详细信息
     */
    @Column(name = "DETAIL", length = 2000)
    private String Detail;
    
    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private UserEntity user;
    
    public UserDateEntity() {
    }
    
    public UserDateEntity(Long id, String detail, UserEntity user) {
        this.id = id;
        Detail = detail;
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDetail() {
        return Detail;
    }
    
    public void setDetail(String detail) {
        Detail = detail;
    }
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser(UserEntity user) {
        this.user = user;
    }
}