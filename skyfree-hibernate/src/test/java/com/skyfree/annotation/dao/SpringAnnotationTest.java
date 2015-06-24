package com.skyfree.annotation.dao;

import com.skyfree.annotation.model.ViewSpace;
import com.skyfree.annotation.dao.ViewSpaceHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/24 11:32
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringAnnotationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ViewSpaceHibernateDao viewSpaceHibernateDao;

    @Test
    public void addViewSpace() throws IOException {
        ViewSpace vs = new ViewSpace();
        vs.setText("baotingfang");
        vs.setDescription("desc");

        ClassPathResource res = new ClassPathResource("image.jpg");
        byte[] image = FileCopyUtils.copyToByteArray(res.getFile());
        vs.setImage(image);
        
        Integer id = viewSpaceHibernateDao.addViewSpace(vs);
        assertEquals(1, (int) id);

        ViewSpace vs1 = viewSpaceHibernateDao.getViewSpace(1);
        assertEquals("baotingfang", vs1.getText());
        
        File file = new File("bb.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(vs1.getImage(), 0, vs.getImage().length);
        fos.flush();
        fos.close();
        
        vs1.setText("hanjing");
        viewSpaceHibernateDao.updateViewSpace(vs1);

        ViewSpace vs2 = viewSpaceHibernateDao.getViewSpace(1);
        assertEquals("hanjing", vs2.getText());

        List<ViewSpace> vss = viewSpaceHibernateDao.findViewSpaceByName("hanjing");
        assertEquals(1, vss.size());
        assertEquals("hanjing", vss.get(0).getText());        
        
        long count = viewSpaceHibernateDao.getViewSpaceCount();
        assertEquals(1, count);
        
        viewSpaceHibernateDao.deleteViewSpace(vs2);

        ViewSpace vs3 = viewSpaceHibernateDao.getViewSpace(1);
        assertNull(vs3);
    }
}
