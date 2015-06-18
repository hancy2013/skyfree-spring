package com.skyfree.exception;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 16:11
 */
public class TransactionManager implements ThrowsAdvice {
    
    /*
    1. 方法名: 必须为afterThrowing
    2. 支持的方法签名: 支持3个参数, 4个参数, 1个参数
        前三个参数为:
                1. 原来的方法
                2. 原来方法的调用参数
                3. 原来的类的实例
                4. 抛出的异常
        这3个参数要么全部提供,要么全部不提供
        第4个参数为Throwable或其子类
        
        增强中可以包括多个afterThrowing方法,spring会找最相近的进行调用
    * */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("抛出异常...");
        System.out.println("进行回滚操作");
    }
}
