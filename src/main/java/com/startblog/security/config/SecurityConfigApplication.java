package com.startblog.security.config;

import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import com.sun.net.httpserver.Authenticator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhaorc
 * @create 2020-04-20 10:33
 */
@Configuration
public class SecurityConfigApplication extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/usermanage/login","/api/usermanage/register").permitAll()///api/userManage/login
            .anyRequest().authenticated();
    
        
    }
}
