package com.startblog.sys.user.enums;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月23日
 */
public enum UserStatus {
    /**
     * 正常
     */
    NORMAL(0, "正常"),

    /**
     * 停用
     */
    DEACTIVATE(1, "停用"),
    
    /**
     * 注销
     */
    CANCELLATION(2, "注销"),
    
    /**
     * 锁定
     */
    LOCKED(3, "锁定"),
    
    /**
     * 账号过期
     */
    ACCOUNT_EXPIRED(4, "账号过期"),
    
    /**
     * 密码过期
     */
    PW_EXPIRED(5, "密码过期"),
    
    /**
     * 证书过期
     */
    CREDENTIALS_EXPIRED(6, "证书过期");
    
    private int index;
    private String name;
    
    
    UserStatus(int index, String name) {
        this.index = index;
        this.name = name;
    }
    
    public int getIndex() {
        return index;
    }
    
    
    public String getName() {
        return name;
    }
}
