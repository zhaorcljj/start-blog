package com.startblog.app.common.base.service;

import com.startblog.app.common.base.entity.BaseEntity;
import com.startblog.data.jpa.GenericRepository;

import java.io.Serializable;

/**
 * 通用service: 与BaseEntity对应。 <br/>
 * 此Service的方法会处理BaseEntity的通用字段。<br/>
 * 继承BaseEntity的实体类对应的service， 应继承此类BaseService。
 * @author zhaorc
 * @version 1.0, 2022年05月31日
 */
public interface BaseService <T extends BaseEntity, ID extends Serializable, D extends GenericRepository<T, ID>>
        extends GenericService<T, ID, D> {
    
    /**
     * 根据id删除数据 -- 逻辑删除
     * @param id 实体类id
     * @return 删除的数据条数
     */
    int deleteById(ID id);
    
    /**
     * 根据id删除数据 -- 逻辑删除
     * @param ids
     * @return 删除的数据条数
     */
    int deleteByIds(Iterable<ID> ids);
    
    /**
     * 新建或更新实体类。<br/>
     * 需要额外处理业务数据逻辑的话，请优先重写{@link #createOrUpdate(T entity)}方法，需要与当前账户有关系的逻辑，再重写此方法。
     * @param entity
     * @param account 当前用户账号
     */
    T createOrUpdate(T entity, String account);
}
