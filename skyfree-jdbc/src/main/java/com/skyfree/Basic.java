package com.skyfree;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 14:44
 */
public class Basic {
    public static void main(String[] args) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/sampledb");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        
        // JdbcTemplate: 线程安全
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(ds);
        
        String sql = "create table t_test(id int primary key, text varchar)";
        jdbcTemplate.execute(sql);
    }
}
