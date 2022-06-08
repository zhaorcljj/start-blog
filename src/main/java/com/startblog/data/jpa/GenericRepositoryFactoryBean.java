package com.startblog.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <pre>
 * 代替默认的{@link org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean}
 * GenericRepositoryFactoryBean负责返回一个RepositoryFactory，
 * Spring Data Jpa 将使用RepositoryFactory来创建Repository具体实现</pre>
 * @author zhaorc
 * @version 1.0, 2022年06月02日
 */
public class GenericRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {
    public GenericRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }
    
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new GenericRepositoryFactory(entityManager);
    }
}
