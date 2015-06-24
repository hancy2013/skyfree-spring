package com.skyfree.annotation.dao;

import com.skyfree.annotation.model.ViewSpace;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/24 11:11
 */
@Repository(value = "annotation_vsd")
public class ViewSpaceHibernateDao extends BaseDao {
    /**
     * 返回的是新建对象的ID值
     *
     * @param vs
     * @return
     */
    @Transactional
    public Integer addViewSpace(ViewSpace vs) {
        return (Integer) getHibernateTemplate().save(vs);
    }

    @Transactional
    public void updateViewSpace(ViewSpace vs) {
        getHibernateTemplate().update(vs);
    }

    @Transactional
    public ViewSpace getViewSpace(int ViewSpaceId) {
        return getHibernateTemplate().get(ViewSpace.class, ViewSpaceId);
    }

    @Transactional
    public void deleteViewSpace(ViewSpace vs) {
        getHibernateTemplate().delete(vs);
    }

    @Transactional
    public List<ViewSpace> findViewSpaceByName(String text) {
        return (List<ViewSpace>) getHibernateTemplate().find("from ViewSpace v where v.text like ?", "%" + text + "%");
    }

    @Transactional
    public Long getViewSpaceCount() {
        Iterator ret = getHibernateTemplate().iterate("select count(*) from com.skyfree.annotation.model.ViewSpace v");
        return (Long) ret.next();
    }
}
