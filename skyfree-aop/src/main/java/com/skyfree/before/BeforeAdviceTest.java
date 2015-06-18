package com.skyfree.before;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 15:26
 */
public class BeforeAdviceTest {
    private Waiter target;
    private BeforeAdvice advice;
    private ProxyFactory pf;
    
    @BeforeTest
    public void init(){
        // 原来的接口
        target = new NaiveWaiter();
        
        // 增强,包含方法名称和位置信息
        advice = new GreetingBeforeAdvice();
        
        // 代理工厂,设定原来的target,生产出代理对象
        pf = new ProxyFactory();
        
        // 将具体的实例和增强联系起来
        pf.setTarget(target);
        pf.addAdvice(advice);
    }
    
    @Test
    public void beforeAdvice(){
        Waiter proxy = (Waiter)pf.getProxy();
        
        // 原来的target的每个方法都得到了加强
        proxy.greetTo("John");
        proxy.serverTo("Tom");
    }
}
