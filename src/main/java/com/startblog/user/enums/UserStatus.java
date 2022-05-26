package com.startblog.user.enums;

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
     * 锁定
     */
    LOCKOUT(1, "锁定"),
    /**
     * 停用
     */
    DEACTIVATE(2, "停用");
    
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
