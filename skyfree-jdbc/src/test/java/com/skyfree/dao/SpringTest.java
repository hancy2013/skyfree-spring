package com.skyfree.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 15:49
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ViewSpaceDao viewSpaceDao;

    @BeforeMethod
    public void initdb() {
        viewSpaceDao.initDb();
    }

    @Test
    public void addViewSpaceDaoTest() {

        int code = viewSpaceDao.addViewSpace("tester");
        System.out.println(code);

        // 显示了4中update方法
        viewSpaceDao.addViewSpace(2, "baotingfang");
        viewSpaceDao.addViewSpaceWithTypes(3, "hanjing");
        viewSpaceDao.addViewSpaceWithStatement(4, "beibei");
        viewSpaceDao.addViewSpaceWithCreateStatement(5, "skyfree");
    }

    @Test
    public void addViewSpacesTest() {
        List<String> names = new ArrayList<String>();
        names.add("A1");
        names.add("A2");

        viewSpaceDao.addViewSpaces(names);
    }

    @Test
    public void getViewSpaceTest() {
        int id = viewSpaceDao.addViewSpace("China");
        ViewSpace vs = viewSpaceDao.getViewSpace("China");
        assertEquals(id, vs.getId());
        assertEquals("China", vs.getText());
    }

    @Test
    public void getViewSpacesTest() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        List<ViewSpace> vss = viewSpaceDao.getViewSpaces("A");
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }
    }

    @Test
    public void getViewSpacesWithTypesTest() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        List<ViewSpace> vss = viewSpaceDao.getViewSpacesWithTypes("A");
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }
    }

    @Test
    public void getViewSpacesWithStatementCreatorTest() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        List<ViewSpace> vss = viewSpaceDao.getViewSpacesWithStatementCreator("A");
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }
    }

    @Test
    public void getViewSpacesWithStatementTest() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        List<ViewSpace> vss = viewSpaceDao.getViewSpacesWithStatement("A");
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }
    }

    @Test
    public void getViewSpacesWithSQL() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        List<ViewSpace> vss = viewSpaceDao.getViewSpacesWithSQL("A");
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }
    }

    @Test
    public void getViewSpacesWithRowMapper() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        List<ViewSpace> vss = viewSpaceDao.getViewSpacesWithRowMapper("A");
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }

        vss = viewSpaceDao.getViewSpacesWithRowMapperAndTypes("A");
        assertEquals(2, vss.size());
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }

        vss = viewSpaceDao.getViewSpacesWithRowMapperAndSQL("A");
        assertEquals(2, vss.size());
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }

        vss = viewSpaceDao.getViewSpacesWithRowMapperAndStatementSetter("A");
        assertEquals(2, vss.size());
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }

        vss = viewSpaceDao.getViewSpacesWithRowMapperAndStatementCreator("A");
        assertEquals(2, vss.size());
        for (ViewSpace vs : vss) {
            System.out.println(vs.getId() + ":" + vs.getText());
        }
    }

    @Test
    public void getViewSpaceCount() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        int count = viewSpaceDao.getViewSpaceCount();
        assertEquals(2, count);
    }

    @Test
    public void getViewSpaceCountWithParams() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        int count = viewSpaceDao.getViewSpaceCountWithParams("A1");
        assertEquals(1, count);
    }

    @Test
    public void getViewSpaceCountWithParamsAndTypes() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        int count = viewSpaceDao.getViewSpaceCountWithParamsAndTypes("A1");
        assertEquals(1, count);
    }

    @Test
    public void getViewSpaceCount1() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int count = viewSpaceDao.getViewSpaceCount1();
        assertEquals(1, count);
    }

    @Test
    public void getViewSpaceCount1WithParams() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        int count = viewSpaceDao.getViewSpaceCount1WithParams("A1");
        assertEquals(1, count);
    }

    @Test
    public void getViewSpaceCount1WithParamsAndTypes() {
        int id1 = viewSpaceDao.addViewSpace("A1");
        int id2 = viewSpaceDao.addViewSpace("A2");

        int count = viewSpaceDao.getViewSpaceCount1WithParamsAndTypes("A1");
        assertEquals(1, count);
    }
    
    @Test
    public void getViewSpace1() {
        int id1 = viewSpaceDao.addViewSpace("A1");

        ViewSpace vs = viewSpaceDao.getViewSpace1("A1");
        assertEquals(id1, vs.getId());
        assertEquals("A1", vs.getText());
    }

    @AfterMethod
    public void tearDown() throws Exception {
        viewSpaceDao.clearDB();
    }
}
