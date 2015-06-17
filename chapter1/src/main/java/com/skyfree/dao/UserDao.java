package com.skyfree.dao;

import com.skyfree.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 10:56
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password) {
        String sqlStr = " SELECT COUNT(*) FROM t_user " +
                " WHERE user_name = ? AND password = ? ";
        return jdbcTemplate.queryForInt(sqlStr, new Object[]{userName, password});
    }

    public User findUserByUserName(String userName) {
        String sqlStr = " SELECT user_id, user_name FROM t_user WHERE user_name= ?";
        final User user = new User();

        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
            }
        });

        return user;
    }

    public void updateLoginInfo(User user) {
        String sqlStr = " UPDATE t_user SET last_visit = ?, last_ip = ? WHERE user_id = ?";
        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(), user.getLastIp(), user.getUserId()});
    }
}
