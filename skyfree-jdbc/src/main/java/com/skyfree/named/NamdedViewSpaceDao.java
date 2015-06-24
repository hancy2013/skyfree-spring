package com.skyfree.named;

import com.skyfree.model.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 15:48
 */
@Repository
public class NamdedViewSpaceDao {
    @Autowired
    private NamedParameterJdbcTemplate namdedJdbcTemplate;

    public void initDb() {
        String sqlStr = "create table if not EXISTS t_test(id serial primary key, text varchar)";
        namdedJdbcTemplate.getJdbcOperations().execute(sqlStr);
    }

    public void clearDB() {
        String sqlSql = "drop table t_test";
        namdedJdbcTemplate.getJdbcOperations().execute(sqlSql);
    }

    public void addViewSpaceByNamedParams(ViewSpace viewSpace) {
        // sql参数使用名字定义
        final String sql = "INSERT INTO t_test(text) VALUES(:text)";

        // 将实体中的属性,自动映射到命名参数上
        SqlParameterSource sps = new BeanPropertySqlParameterSource(viewSpace);

        namdedJdbcTemplate.update(sql, sps);
    }

    public void addViewSpaceByMapParams(ViewSpace viewSpace) {
        // sql参数使用名字定义
        final String sql = "INSERT INTO t_test(text) VALUES(:text)";

        // 可以使用链式调用
        MapSqlParameterSource map = new MapSqlParameterSource().addValue("text", viewSpace.getText());
        
        namdedJdbcTemplate.update(sql, map);
    }
}
