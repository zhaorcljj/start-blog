package com.startblog.data.jpa;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

/**
 *
 * 继承JpaRepositoryFactory后，把返回的对象修改成自己的实现{@link com.startblog.data.jpa.GenericRepositoryImpl}
 *
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
public class GenericRepositoryFactory extends JpaRepositoryFactory {
    public GenericRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return GenericRepositoryImpl.class;
    }
    
}
