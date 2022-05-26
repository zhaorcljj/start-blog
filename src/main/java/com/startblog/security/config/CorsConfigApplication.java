package com.startblog.security.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author zhaorc
 * @create 2020-04-20 10:33
 */
@Configuration
public class CorsConfigApplication {
    
    @Value("${allowed.origin.path}")
    private String allowedOriginPath;
    
    public String getAllowedOriginPath() {
        return allowedOriginPath;
    }
    
    public void setAllowedOriginPath(String allowedOriginPath) {
        this.allowedOriginPath = allowedOriginPath;
    }
    
    private CorsConfiguration buildConfig(){
        CorsConfiguration configuration = new CorsConfiguration();
        //127.0.0.1 != localhost
        //configuration.addAllowedOrigin("http://127.0.0.1:8088");
        //configuration.addAllowedOrigin("http://localhost:8088");
        System.out.println("allowed.origin.path:" + allowedOriginPath);
        if(StringUtils.isNotBlank(allowedOriginPath)){
            String[] pathArr = allowedOriginPath.split(",");
            for (String path : pathArr) {
                if (StringUtils.isNotBlank(path)) {
                    configuration.addAllowedOrigin(path);
                }
            }
            //跨域请求头
            configuration.addAllowedHeader("*");
            //跨域请求方法
            configuration.addAllowedMethod("*");
    
            configuration.setAllowCredentials(true);
        }

        return  configuration;
    }
    @Bean
    public CorsFilter corsFilter(){
        System.out.println("-----------------------------------------------------------------------");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
