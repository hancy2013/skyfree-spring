package com.skyfree.schema;

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
    public void testSpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("schemaAdvice.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");
    }

    @Test
    public void testSpring1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("schemaAdvice1.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");
    }

    @Test
    public void testSpring2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("schemaAdvice2.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");
    }

    @Test
    public void testSpring3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("introduction.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");
        
        // 这里看到waiter已经实现了Seller接口
        Seller seller = (Seller)waiter;
        seller.sell();
    }

    @Test
    public void testSpring4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("schemaAdvice3.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");
    }

    @Test
    public void testSpring5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("advisor.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");
    }
}
