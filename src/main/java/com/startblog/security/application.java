package com.startblog.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhaorc
 * @create 2020-04-20 10:33
 */
@Configuration
public class application extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test/hello").permitAll()
                .anyRequest().authenticated();
        /*http.antMatcher("/startblog")
                .authorizeRequests().antMatchers("/test/hello")
                .permitAll()
                .anyRequest()
                .authenticated();*/
    }
}
