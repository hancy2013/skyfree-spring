package com.skyfree.annotation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 18:06
 */
@Aspect
public class PostGreetingAspect {

    /**
     * @param name 原来方法的传入参数
     * @param ret  原来方法的返回结果
     */
    @AfterReturning(value = "execution(* greetTo(java.lang.String)) && args(name)", argNames = "name,ret", returning = "ret")
    public void beforeGreeting(String name, Object ret) {
        System.out.println(ret);
        System.out.println("Bye Mr." + name + "...");
    }
}
