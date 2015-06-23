package com.skyfree.lob;

import com.skyfree.model.ViewPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 15:48
 */
@Repository
public class ViewPointDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LobHandler lobHandler;

    public void initDb() {
        String sqlStr = "create table if not EXISTS t_vp(id serial primary key, text varchar, image bytea, description VARCHAR)";
        jdbcTemplate.execute(sqlStr);
    }

    public void clearDB() {
        String sqlSql = "drop table t_vp";
        jdbcTemplate.execute(sqlSql);
    }

    // 显示了如何保存二进制对象
    public void addViewPoint(final ViewPoint vp) {
        String sql = "INSERT INTO t_vp(text, image, description) VALUES(?,?,?)";
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
            @Override
            protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                preparedStatement.setString(1, vp.getText());
                lobCreator.setBlobAsBytes(preparedStatement, 2, vp.getImage());
                // 在pg中好像没有找到对应的概念,以后再查查
                lobCreator.setClobAsString(preparedStatement, 3, vp.getDescription());
            }
        });
    }

    // 显示了如何读取二进制对象,这个操作有个缺点,会将二进制对象全部加载到内存,后果可以想象.
    public ViewPoint getViewPointAsBlock(String text) {
        String sql = "SELECT id, text, image, description FROM t_vp WHERE text = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{text}, new RowMapper<ViewPoint>() {
            public ViewPoint mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewPoint vp = new ViewPoint();
                vp.setId(resultSet.getInt("id"));
                vp.setText(resultSet.getString("text"));
                // 这是关键部分
                byte[] image = lobHandler.getBlobAsBytes(resultSet, "image");
                vp.setImage(image);
                vp.setDescription(resultSet.getString("description"));

                return vp;
            }
        });
    }


    public void getViewPointAsStream(String text, final OutputStream os) {
        String sql = "SELECT id, text, image, description FROM t_vp WHERE text = ?";
        jdbcTemplate.query(sql, new Object[]{text}, new AbstractLobStreamingResultSetExtractor() {
            @Override
            protected void streamData(ResultSet resultSet) throws SQLException, IOException, DataAccessException {
                InputStream is = lobHandler.getBlobAsBinaryStream(resultSet, "image");
                if (is != null) {
                    FileCopyUtils.copy(is, os);
                }
            }

            @Override
            protected void handleNoRowFound() throws DataAccessException {
                // super.handleNoRowFound();
                System.out.println("没有查到结果");
            }

            @Override
            protected void handleMultipleRowsFound() throws DataAccessException {
                // super.handleMultipleRowsFound();
                System.out.println("查到多行结果");
            }
        });
    }


}
