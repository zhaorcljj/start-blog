package com.startblog.app.common.base.service.impl;

import com.startblog.app.common.base.service.BaseService;
import com.startblog.app.common.base.service.GenericService;
import com.startblog.data.jpa.GenericRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * 通用service实现； 实体类对应的service实现类， 可以继承此service。<br/>
 * 如果继承了BaseEntity，可考虑继承BaseServiceImpl @see com。
 * @see GenericService
 * @see BaseService
 * @see BaseServiceImpl
 * @author zhaorc
 * @version 1.0, 2022年06月01日
 * Type parameters: <T>-实体类类型
 *                  <ID>-实体类ID类型
 *                  <D>-实体Dao类型
 */
public class GenericServiceImpl <T, ID extends Serializable, D extends GenericRepository<T, ID>>
        implements GenericService<T, ID, D> , InitializingBean {
    
    protected D dao;
    protected EntityManager em;
    
    @PersistenceContext
    public final void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    public final EntityManager getEntityManager() {
        return this.em;
    }
    
    public D getDao() {
        return dao;
    }
    
    @Autowired
    public void setDao(D dao) {
        this.dao = dao;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dao, "dao is required!");
        Assert.notNull(em, "EntityManager is required!");
    }
    
    
    @Override
    public T getById(ID id) {
        return dao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void persist(T entity) {
        dao.persist(entity);
    }
    
    @Override
    @Transactional
    public T merge(T entity) {
        return dao.merge(entity);
    }
    
    @Override
    public void detach(T entity) {
        em.detach(entity);
    }
    
    @Override
    public long getCount() {
        return dao.count();
    }
    
    @Override
    public long getCountBySpec(Specification<T> spec) {
        return dao.count(spec);
    }
    
    @Override
    @Transactional
    public int removeById(ID id) {
        if(dao.existsById(id)) {
            dao.deleteById(id);
            return 1;
        }else {
            return 0;
        }
    }
    
    @Override
    @Transactional
    public void persistAndFlush(T entity) {
        dao.persistAndFlush(entity);
    }
    
    @Override
    @Transactional
    public void persist(Iterable<T> entities) {
        dao.persist(entities);
    }
    
    @Override
    @Transactional
    public T mergeAndFlush(T entity) {
        return dao.mergeAndFlush(entity);
    }
    
    @Override
    @Transactional
    public Iterable<T> merge(Iterable<T> entities) {
        return dao.merge(entities);
    }
    
    @Override
    @Transactional
    public int removeByIds(Iterable<ID> ids) {
        int count = 0;
        for (ID id : ids) {
            count += this.removeById(id);
        }
        return count;
    }
    
    @Override
    public List<T> findList(Specification<T> spec, int startPosition, int maxResults, Sort sort) {
        return dao.findList(spec, startPosition, maxResults, sort);
    }
    
    @Override
    public List<T> findList(Specification<T> spec) {
        return dao.findAll(spec);
    }
    
    @Override
    public List<T> findByIds(Iterable<ID> ids) {
        return dao.findAllById(ids);
    }
    
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }
    
    @Override
    public List<T> findAll(Sort sort) {
        return dao.findAll(sort);
    }
}
