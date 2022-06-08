package com.startblog.data.jpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaorc
 * @version 1.0, 2022年06月01日
 */
public class GenericRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements GenericRepository<T, ID>{
    
    private final EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }
    
    public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }
    
    @Override
    public List<T> findList(Specification<T> spec, int startPosition, int maxResults, Sort sort) {
        TypedQuery<T> query = getQuery(spec, sort == null ? Sort.unsorted() : sort);
        if (startPosition > 0) {
            query.setFirstResult(startPosition);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }
    
    @Override
    @Transactional
    public void persist(T entity) {
        em.persist(entity);
    }
    
    @Override
    @Transactional
    public void persistAndFlush(T entity) {
        persist(entity);
        flush();
    }
    
    @Override
    @Transactional
    public void persist(Iterable<T> entities) {
        if (entities == null) {
            return;
        }
        for (T entity : entities) {
            persist(entity);
        }
    }
    
    @Override
    @Transactional
    public T merge(T entity) {
        return em.merge(entity);
    }
    
    @Override
    @Transactional
    public T mergeAndFlush(T entity) {
        T result = merge(entity);
        flush();
        return result;
    }
    
    @Override
    @Transactional
    public Iterable<T> merge(Iterable<T> entities) {
        List<T> result = new ArrayList<T>();
        if (entities == null) {
            return result;
        }
        for (T entity : entities) {
            result.add(merge(entity));
        }
        return result;
    }
}
