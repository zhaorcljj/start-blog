package com.startblog.entity;

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
    
    @Column(name = "CREATOR")
    private String creator;
    
    @Column(name = "CREATION_TIME", updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Column(name = "LAST_MODIFY_TIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastModifyTime;
    
    @Column(name = "DELETED")
    private int deleted;
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public Date getCreationTime() {
        return creationTime;
    }
    
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public Date getLastModifyTime() {
        return lastModifyTime;
    }
    
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
    
    public int getDeleted() {
        return deleted;
    }
    
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    public BaseEntity(String creator, Date creationTime, Date lastModifyTime, int deleted) {
        this.creator = creator;
        this.creationTime = creationTime;
        this.lastModifyTime = lastModifyTime;
        this.deleted = deleted;
    }
    
    public BaseEntity() {
    }
}
