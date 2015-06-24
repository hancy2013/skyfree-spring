package com.skyfree.named;

import com.skyfree.model.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 15:49
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringNamedTest extends AbstractTestNGSpringContextTests {

    @Autowired
    NamdedViewSpaceDao viewSpaceDao;

    @BeforeMethod
    public void initdb() {
        viewSpaceDao.initDb();
    }

    @Test
    public void addViewSpaceDaoTest() {
        ViewSpace vs = new ViewSpace();
        vs.setText("ttt123");

        viewSpaceDao.addViewSpaceByNamedParams(vs);
    }

    @Test
    public void addViewSpaceDao1Test() {
        ViewSpace vs = new ViewSpace();
        vs.setText("ttt1234");

        viewSpaceDao.addViewSpaceByNamedParams(vs);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        viewSpaceDao.clearDB();
    }
}
