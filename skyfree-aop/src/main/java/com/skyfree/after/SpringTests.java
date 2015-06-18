package com.skyfree.after;

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
        String configPath = "afterAdvice.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter)context.getBean("waiter");
        waiter.greetTo("John");
        System.out.println("============");
        
        // 参看afterAdvice.xml,同时使用了两个advice
        Waiter waiter1 =(Waiter)context.getBean("waiter1");
        waiter1.greetTo("Tom");
    }
}
