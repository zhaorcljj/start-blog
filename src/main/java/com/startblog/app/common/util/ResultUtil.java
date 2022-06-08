package com.startblog.app.common.util;

import com.startblog.app.common.bean.RestResult;
import com.startblog.app.common.enums.ResultCode;

/**
 * @author zhaorc
 * @version 1.0, 2022年05月27日
 */
public class ResultUtil {
    public static RestResult success() {
        return new RestResult(true);
    }
    
    public static <T> RestResult<T> success(T data) {
        return new RestResult(true, data);
    }
    
    public static RestResult fail() {
        return new RestResult(false);
    }
    
    public static RestResult fail(ResultCode resultEnum) {
        return new RestResult(false, resultEnum);
    }

}
