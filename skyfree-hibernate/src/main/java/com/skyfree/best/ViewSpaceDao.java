package com.skyfree.best;

import com.skyfree.model.ViewSpace;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/25 18:02
 */
public class ViewSpaceDao extends BaseDao<ViewSpace> {

    // 从base自动实现了CRUD操作, 这里只需要实现自己的特定方法即可

    public long getViewSpaceCount() {
        Object obj = getHibernateTemplate().iterate("SELECT COUNT(*) FROM com.skyfree.model.ViewSpace").next();
        return (Long) obj;
    }
}
