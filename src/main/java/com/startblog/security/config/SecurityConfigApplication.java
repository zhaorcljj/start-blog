package com.startblog.security.config;

import com.startblog.security.user.common.CustomAuthenticationEntryPoint;
import com.startblog.security.user.service.impl.UserDetailsServiceImpl;
import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import com.sun.net.httpserver.Authenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author zhaorc
 * @create 2020-04-20 10:33
 */
@Configuration
// @EnableWebSecurity
public class SecurityConfigApplication extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/api/usermanage/login","/api/usermanage/register")
            .permitAll()
            .antMatchers("/api/usermanage/query")
            .hasAnyRole("ADMIN", "USER")
            .anyRequest().authenticated()
            .and().exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint()); //匿名用户访问无权限资源时的异常处理
    
        
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用默认的BCryptPasswordEncoder加密方案
        return new BCryptPasswordEncoder();
        //不使用密码加密
        //return NoOpPasswordEncoder.getInstance();
        //strength=10，即密钥的迭代次数(strength取值在4~31之间，默认为10)
        //return new BCryptPasswordEncoder(10);
        //利用工厂类PasswordEncoderFactories实现,工厂类内部采用的是委派密码编码方案.
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
