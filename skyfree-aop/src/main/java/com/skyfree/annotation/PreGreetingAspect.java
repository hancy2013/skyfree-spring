package com.skyfree.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/17 18:06
 */
@Aspect
public class PreGreetingAspect {

    @Before(value = "execution(* greetTo(java.lang.String)) && args(name)", argNames = "name")
    public void beforeGreeting(String name) {
        System.out.println("How are you! Mr." + name + "...");
    }
}
