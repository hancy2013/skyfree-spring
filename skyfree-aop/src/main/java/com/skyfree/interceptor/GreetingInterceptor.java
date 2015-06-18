package com.skyfree.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 16:04
 */
public class GreetingInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 原来方法的调用参数
        Object[] args = methodInvocation.getArguments();
        String clientName = (String) args[0];
        
        // 前置处理
        System.out.println("How are you! Mr. " + clientName + ".");
        
        // 调用原来的方法
        Object obj = methodInvocation.proceed();
        
        // 后置处理
        System.out.println("Please enjoy yourself!");
        
        // 返回原来方法的结果
        return obj;
    }
}
