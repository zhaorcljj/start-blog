package com.startblog.app.common.base.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月23日
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1243026396014847180L;
    
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
    /**
     * 删除标识
     */
    @Column(name = "DELETED")
    private int deleted;
    
    public BaseEntity(Long creatorId, String creatorAccount, String creatorName, Long modifyUserId, String modifyUserAccount, String modifyUserName, Long creationTime, Long lastModifyTime, int deleted) {
        this.creatorId = creatorId;
        this.creatorAccount = creatorAccount;
        this.creatorName = creatorName;
        this.modifyUserId = modifyUserId;
        this.modifyUserAccount = modifyUserAccount;
        this.modifyUserName = modifyUserName;
        this.creationTime = creationTime;
        this.lastModifyTime = lastModifyTime;
        this.deleted = deleted;
    }
    
    public BaseEntity() {
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
    
    public int getDeleted() {
        return deleted;
    }
    
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    @Override
    public String toString() {
        return "BaseEntity{" +
                "creatorId=" + creatorId +
                ", creatorAccount='" + creatorAccount + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", modifyUserId=" + modifyUserId +
                ", modifyUserAccount='" + modifyUserAccount + '\'' +
                ", modifyUserName='" + modifyUserName + '\'' +
                ", creationTime=" + creationTime +
                ", lastModifyTime=" + lastModifyTime +
                ", deleted=" + deleted +
                '}';
    }
}
