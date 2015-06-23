package com.skyfree.lob;

import com.skyfree.model.ViewPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.io.*;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/18 15:49
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
@TransactionConfiguration
@Transactional
public class SpringLobTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ViewPointDao viewPointDao;

    @BeforeMethod
    public void initdb() {
        viewPointDao.initDb();
    }

    @Test
    @Rollback(false)
    public void addViewPointTest() throws IOException {
        ViewPoint vp = new ViewPoint();
        vp.setText("abc");

        // 这里显示了加载资源的方式,任何资源都可以使用这样的方法进行加载
        ClassPathResource res = new ClassPathResource("image.jpg");
        byte[] image = FileCopyUtils.copyToByteArray(res.getFile());
        vp.setImage(image);
        vp.setDescription("测试描述");

        viewPointDao.addViewPoint(vp);
    }

    @Test
    @Rollback(false)
    public void getViewPointAsBlockTest() throws IOException {
        ViewPoint vp = new ViewPoint();
        vp.setText("abc");

        // 这里显示了加载资源的方式,任何资源都可以使用这样的方法进行加载
        ClassPathResource res = new ClassPathResource("image.jpg");
        byte[] image = FileCopyUtils.copyToByteArray(res.getFile());

        int before_length = image.length;

        vp.setImage(image);
        vp.setDescription("测试描述");

        viewPointDao.addViewPoint(vp);

        ViewPoint vp1 = viewPointDao.getViewPointAsBlock("abc");

        assertEquals("abc", vp1.getText());
        assertEquals("测试描述", vp1.getDescription());
        assertEquals(before_length, vp1.getImage().length);
    }

    @Test
    @Rollback(false)
    public void getViewPointAsStreamTest() throws IOException {
        ViewPoint vp = new ViewPoint();
        vp.setText("abc");

        // 这里显示了加载资源的方式,任何资源都可以使用这样的方法进行加载
        ClassPathResource res = new ClassPathResource("image.jpg");
        byte[] image = FileCopyUtils.copyToByteArray(res.getFile());

        int before_length = image.length;

        vp.setImage(image);
        vp.setDescription("测试描述");

        viewPointDao.addViewPoint(vp);

        // 这里依赖了强大的FileCopyUtils.copy(is, os)的方式
        FileOutputStream fs = new FileOutputStream("b.jpg");
        BufferedOutputStream os = new BufferedOutputStream(fs);

        viewPointDao.getViewPointAsStream("abc", os);
        
        // 下面是另外一种读取二进制的方式,也是将数据装载到内存中
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        viewPointDao.getViewPointAsStream("abc", bos);
        byte[] image1 = bos.toByteArray();
        assertEquals(before_length, image1.length);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        viewPointDao.clearDB();
    }
}
