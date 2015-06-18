package com.skyfree.after;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 15:53
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        // o: 目标实例方法返回的结果
        // method: 目标实例中的方法
        // objects: 目标实例方法中的调用参数
        // o1: 目标类的实例
        // 如果在增强中抛出了异常,如果该异常是目标方法中声明的异常,则归并到目标方法中,如果不是,则转为运行时异常,并抛出异常
        System.out.println("Please enjoy yourself!");
    }
}
