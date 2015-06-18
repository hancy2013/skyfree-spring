package com.skyfree.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 15:44
 */
public class SpringTests {
    @Test
    public void testAdvice(){
        String configPath = "staticAdvisor.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)context.getBean("waiter");
        waiter.greetTo("John");

        System.out.println("=====");
        // 这个也不会有加强的效果
        waiter.serveTo("Bob");
        System.out.println("=====");
        // 这个不会有加强的效果
        Seller seller =(Seller)context.getBean("seller");
        seller.greetTo("Tom");
    }
}
