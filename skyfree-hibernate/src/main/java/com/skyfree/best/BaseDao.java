package com.skyfree.best;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/25 17:57
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private Class entityClass;

    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    public T get(Serializable id) {
        return (T) hibernateTemplate.get(entityClass, id);
    }

    public void save(T entity) {
        hibernateTemplate.save(entity);
    }

    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
}
