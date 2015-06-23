package com.skyfree.dao;

import com.skyfree.model.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 15:48
 */
@Repository
public class ViewSpaceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void initDb() {
        String sqlStr = "create table if not EXISTS t_test(id serial primary key, text varchar)";
        jdbcTemplate.execute(sqlStr);
    }

    public void clearDB() {
        String sqlSql = "drop table t_test";
        jdbcTemplate.execute(sqlSql);
    }

    /**
     * 最一般的insert操作
     * 尽量使用绑定参数形式,可以复用SQL执行计划,提高效率
     * SQL查询的字符串尽量定义在类级别(final static), 提高JVM的内存使用效率.
     *
     * @param id
     * @param text
     */
    public void addViewSpace(int id, String text) {
        String sql = "INSERT INTO t_test(id, text) values(?,?)";
        jdbcTemplate.update(sql, new Object[]{id, text});
    }

    public void addViewSpaceWithTypes(int id, String text) {
        String sql = "INSERT INTO t_test(id, text) values(?,?)";
        jdbcTemplate.update(sql, new Object[]{id, text}, new int[]{Types.INTEGER, Types.VARCHAR});
    }

    public void addViewSpaceWithStatement(final int id, final String text) {
        String sql = "INSERT INTO t_test(id, text) values(?,?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, text);
            }
        });
    }

    public void addViewSpaceWithCreateStatement(final int id, final String text) {
        final String sql = "INSERT INTO t_test(id, text) values(?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, text);
                return ps;
            }
        });
    }

    // 这里显示了获取数据库生成的主键ID的方法
    public int addViewSpace(final String text) {
        final String sql = "INSERT INTO t_test(text) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                // 这里的String字符串数组非常重要,不然不返回结果
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, text);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // 这里显示了批量执行sql的操作
    public void addViewSpaces(final List<String> names) {
        final String sql = "INSERT INTO t_test(text) values(?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                String name = names.get(i);
                preparedStatement.setString(1, name);
            }

            public int getBatchSize() {
                // 此批次一共要提交的记录数目
                return names.size();
            }
        });
    }

    // 单条记录查询
    public ViewSpace getViewSpace(String text) {
        String sql = "SELECT id, text FROM t_test WHERE text=?";
        final ViewSpace vs = new ViewSpace();

        jdbcTemplate.query(sql, new Object[]{text}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
            }
        });

        return vs;
    }

    // 查询多条记录, like 模糊查询, 这里要注意like参数的构造方法
    public List<ViewSpace> getViewSpaces(String partText) {
        String sql = "SELECT id, text FROM t_test WHERE text like ?";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(sql, new Object[]{"%" + partText + "%"}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                vss.add(vs);
            }
        });

        return vss;
    }

    // 查询多条记录, like 模糊查询, 这里要注意like参数的构造方法, 类型安全的类型
    public List<ViewSpace> getViewSpacesWithTypes(String partText) {
        String sql = "SELECT id, text FROM t_test WHERE text like ?";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(sql, new Object[]{"%" + partText + "%"}, new int[]{Types.VARCHAR}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                vss.add(vs);
            }
        });

        return vss;
    }

    // 查询多条记录, like 模糊查询, 这里要注意like参数的构造方法, 使用PreparedStatementCreator
    public List<ViewSpace> getViewSpacesWithStatementCreator(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like ?";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + partText + "%");
                return ps;
            }
        }, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                vss.add(vs);
            }
        });

        return vss;
    }

    // 查询多条记录, like 模糊查询, 这里要注意like参数的构造方法, 使用PreparedStatementCreator
    public List<ViewSpace> getViewSpacesWithStatement(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like ?";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, "%" + partText + "%");
            }
        }, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));

                vss.add(vs);
            }
        });

        return vss;
    }


    // 查询多条记录, like 模糊查询, 这里要注意like参数的构造方法, 这总觉得不安全
    public List<ViewSpace> getViewSpacesWithSQL(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like '%" + partText + "%'";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));

                vss.add(vs);
            }
        });

        return vss;
    }

    // 这个写法就很简洁了
    public List<ViewSpace> getViewSpacesWithRowMapper(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like ?";

        return jdbcTemplate.query(sql, new Object[]{"%" + partText + "%"}, new RowMapper<ViewSpace>() {
            public ViewSpace mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                return vs;
            }
        });
    }

    // 添加了参数类型的校验
    public List<ViewSpace> getViewSpacesWithRowMapperAndTypes(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like ?";

        return jdbcTemplate.query(sql, new Object[]{"%" + partText + "%"}, new int[]{Types.VARCHAR}, new RowMapper<ViewSpace>() {
            public ViewSpace mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                return vs;
            }
        });
    }

    // 直接上SQL,总觉得不安全
    public List<ViewSpace> getViewSpacesWithRowMapperAndSQL(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like '%" + partText + "%'";

        return jdbcTemplate.query(sql, new RowMapper<ViewSpace>() {
            public ViewSpace mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                return vs;
            }
        });
    }

    public List<ViewSpace> getViewSpacesWithRowMapperAndStatementSetter(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like ?";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, "%" + partText + "%");
            }
        }, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                vss.add(vs);
            }
        });

        return vss;
    }

    public List<ViewSpace> getViewSpacesWithRowMapperAndStatementCreator(final String partText) {
        final String sql = "SELECT id, text FROM t_test WHERE text like ?";
        final List<ViewSpace> vss = new ArrayList<ViewSpace>();

        jdbcTemplate.query(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + partText + "%");
                return ps;
            }
        }, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                vss.add(vs);
            }
        });

        return vss;
    }

    // long类型的类似int
    // 如果返回结果为空,抛出EmptyResultDataAccessException, 如果多于1行记录,抛出IncorrectResultSizeDataAccessException
    public int getViewSpaceCount() {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM t_test");
    }

    public int getViewSpaceCountWithParams(String text) {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM t_test WHERE text = ?", new Object[]{text});
    }

    public int getViewSpaceCountWithParamsAndTypes(String text) {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM t_test WHERE text = ?", new Object[]{text}, new int[]{Types.VARCHAR});
    }

    // queryForObject, 可以查询一下, 可以将一条记录转成一个对象, 主要是RowMapper接口    
    public int getViewSpaceCount1() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_test", Integer.class);
    }

    public int getViewSpaceCount1WithParams(String text) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_test WHERE text=?", new Object[]{text}, Integer.class);
    }

    public int getViewSpaceCount1WithParamsAndTypes(String text) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_test WHERE text=?", new Object[]{text}, new int[]{Types.VARCHAR}, Integer.class);
    }

    // 这已是最全参数的形式了,其他形式灵活应用
    public ViewSpace getViewSpace1(String text) {
        return jdbcTemplate.queryForObject("SELECT * FROM t_test WHERE text =?", new Object[]{text}, new RowMapper<ViewSpace>() {
            public ViewSpace mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewSpace vs = new ViewSpace();
                vs.setId(resultSet.getInt("id"));
                vs.setText(resultSet.getString("text"));
                return vs;
            }
        });
    }
}
