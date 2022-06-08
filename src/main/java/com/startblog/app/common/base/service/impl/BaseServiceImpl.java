package com.startblog.app.common.base.service.impl;

import com.startblog.app.common.base.entity.BaseEntity;
import com.startblog.data.jpa.GenericRepository;
import com.startblog.app.common.base.service.BaseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 通用service实现: 与BaseEntity对应 <br/>
 * 此Service的方法会处理BaseEntity的通用字段。<br/>
 * 继承BaseEntity的实体类对应的service实现， 应继承此类BaseServiceImpl
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable, D extends GenericRepository<T, ID>>
        extends GenericServiceImpl<T, ID, D> implements BaseService<T, ID, D>, InitializingBean {
    
    @Override
    public void afterPropertiesSet() throws Exception {
    
    }
    
    @Override
    @Transactional
    public int deleteById(ID id) {
        T entity = getById(id);
        if (entity != null) {
            entity.setDeleted(1);
            mergeAndFlush(entity);
            return 1;
        }
        return 0;
        
    }
    
    @Override
    @Transactional
    public int deleteByIds(Iterable<ID> ids) {
        int count = 0;
        for (ID id : ids) {
            count += this.deleteById(id);
        }
        return count;
    }
    
    @Override
    @Transactional
    public T createOrUpdate(T entity, String account) {
        return null;
    }
}
