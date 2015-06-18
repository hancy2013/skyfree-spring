package com.skyfree.annotation;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
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
    public void testAdvice() {
        Waiter waiter = new NaiveWaiter();
        AspectJProxyFactory pf = new AspectJProxyFactory();

        pf.setTarget(waiter);
        pf.addAspect(PreGreetingAspect.class);

        Waiter proxy = pf.getProxy();
        proxy.greetTo("John");
        System.out.println("=====");
        proxy.serverTo("Tom");
    }
    
    @Test
    public void testSpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aspect.xml");
        Waiter waiter =(Waiter)context.getBean("waiter");
        waiter.greetTo("baotingfang");
        System.out.println("=====");
        waiter.serverTo("beibei");

    }
}
