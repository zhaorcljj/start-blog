package com.startblog.security.user.common;

import com.alibaba.fastjson.JSON;
import com.startblog.app.common.bean.RestResult;
import com.startblog.app.common.enums.ResultCode;
import com.startblog.app.common.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月07日
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        RestResult fail = ResultUtil.fail(ResultCode.USER_NOT_LOGIN);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(fail));
    }
}
