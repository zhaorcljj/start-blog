package com.startblog.sys.user.enums;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月01日
 */
public enum UserEnabled {
    /**
     * 正常
     */
    DISABLED(0, "禁用"),
    /**
     * 正常
     */
    ENABLED(1, "启用");
    
    private int index;
    private String name;
    
    
    UserEnabled(int index, String name) {
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
