package com.skyfree.aspect.autoproxy;

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
        // 在regex包中就没有定义advisor了
        String configPath = "autoProxy.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)context.getBean("waiter");
        waiter.greetTo("John");

        System.out.println("=====");
        // 这个也不会有加强的效果
        waiter.serveTo("Bob");
        System.out.println("=====");
        // 这个也有加强的效果,因为所有以er结尾的bean都可以在greetTo上增强
        com.skyfree.aspect.advisor.autoproxy.Seller seller =(com.skyfree.aspect.advisor.autoproxy.Seller)context.getBean("seller");
        seller.greetTo("Tom");
    }
}
