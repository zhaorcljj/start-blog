package com.startblog.app.common.base.service;

import com.startblog.app.common.base.service.impl.BaseServiceImpl;
import com.startblog.app.common.base.service.impl.GenericServiceImpl;
import com.startblog.data.jpa.GenericRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * 通用service； 实体类对应的service， 可以继承此service。<br/>
 * 如果继承了BaseEntity，可考虑继承BaseService。
 *
 * @see GenericServiceImpl
 * @see BaseService
 * @see BaseServiceImpl
 *
 * @author zhaorc
 * @version 1.0, 2022年06月01日
 * Type parameters: <T>-实体类类型
 *                  <ID>-实体类ID类型
 *                  <D>-实体Dao类型
 */
public interface GenericService<T, ID extends Serializable, D extends GenericRepository<T, ID>> {
    
    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    T getById(ID id);
    
    /**
     * 将数据对象插入到数据库中
     * <pre>
     *     // 事务开始
     *     em.persist(e);
     *     // persist后改变对象属性值
     *     e.setSomeField(someValue);
     *     // 事务结束， 字段 e.someField 的改动会更新到数据库
     *     // 原因 : 经过 persist, e 已经是一个 managed 实体对象，处于被修改跟踪的状态
     * </pre>
     * @param entity
     */
    public void persist(T entity);
    
    /**
     * 将数据对象的修改保存到数据库中
     *<pre>
     *      //事务开始
     *      e = new MyEntity();
     *      em.merge(e);
     *      // merge后改变对象属性值
     *      e.setSomeField(anotherValue);
     *      // 事务结束， 字段 e.someField 的修改不会被更新到数据库，
     *      // 原因解释 : 合并过程中有两个对象，合并来源对象，合并目标对象 ，整个合并过程中，参数对象 e
     *      // 作为合并来源对象(而不是合并目标对象),一直都不会设置到`managed`对象，因此合并结束后对
     *      // e.someFiled 的修改也不会被更新到数据库。
     *      // 注意 : merge 方法返回的对象才是合并目标对象 即:
     *          // tran starts 事务开始
     *          e = new T();
     *          T e2 = em.merge(e);
     *          e2.setSomeField(anotherValue);
     *          // 事务结束，作为合并(`merge`)目标对象，
     *          // e2处于`managed`状态，对其属性 e.someField 的修改此时会被更新到数据库
     *</pre>
     * @param entity
     * @return
     */
    public T merge(T entity);
    
    /**
     * 将实体转换为游离态
     * <pre>
     *  从持久性上下文中删除给定实体，导致托管实体分离。
     *  对实体所做的未刷新更改（如果有）（包括删除实体）将不会同步到数据库。
     *  先前引用分离实体的实体将继续引用它。
     * </pre>
     * @param entity
     */
    void detach(T entity);
    
    /**
     * 获取所有数据数量
     * @return
     */
    long getCount();
    
    /**
     *返回满足指定约束的记录数量
     * @param spec
     * @return
     */
    long getCountBySpec(Specification<T> spec);
    
    /**
     * 根据ID删除（物理删除）
     * @param id
     * @return
     */
    int removeById(ID id);
    
    /**
     * 将数据对象插入到数据库中并执行flush操作
     *
     * @param entity
     */
    public void persistAndFlush(T entity);
    
    /**
     * 将数据对象集合插入到数据库中
     *
     * @param entities
     */
    public void persist(Iterable<T> entities);
    
    /**
     * 根据ID集合删除（物理删除）
     * @param ids
     * @return
     */
    int removeByIds(Iterable<ID> ids);
    
    /**
     * 将数据对象的修改保存到数据库中并执行flush操作
     *
     * @param entity
     * @return
     */
    T mergeAndFlush(T entity);
    
    /**
     * 将数据对象集合保存到数据库中
     *
     * @param entities
     * @return
     */
    Iterable<T> merge(Iterable<T> entities);
    
    /**
     * 返回满足指定约束的记录集合
     *
     * @param spec 约束条件
     * @param startPosition 返回结果前跳过的记录数
     * @param maxResults 返回的最大记录条数，如果设置值小于1则返回所有结果
     * @param sort 返回结果时的排序设置
     * @return
     */
    List<T> findList(Specification<T> spec, int startPosition, int maxResults, Sort sort);
    
    /**
     * 返回满足指定约束的记录集合
     * @param spec 约束条件
     * @return
     */
    List<T> findList(Specification<T> spec);
    
    /**
     * 根据ID集合查询记录集合
     * @param ids
     * @return
     */
    List<T> findByIds(Iterable<ID> ids);
    
    /**
     * 返回所有记录
     * @return
     */
    List<T> findAll();
    
    /**
     * 按排序返回所有记录
     * @param sort 排序
     * @return
     */
    List<T> findAll(Sort sort);
    
}
