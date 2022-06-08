package com.startblog.data.jpa.config;

import com.startblog.data.jpa.GenericRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.startblog"}, repositoryFactoryBeanClass = GenericRepositoryFactoryBean.class)
public class GenericRepositoryConfig {
}
