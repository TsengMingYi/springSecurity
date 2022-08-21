package com.example.json;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class MyAspect {

//    @Before("execution(* com.example.json.HpPrinter.*(..))")
//    public void before(){
//        System.out.println("I am before");
//    }
//
//    @After("execution(* com.example.json.HpPrinter.*(..))")
//    public void after(){
//        System.out.println("I am after");
//    }

//    @Around("execution(* com.example.json.HpPrinter.*(..))")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable{
//        Date date = new Date();
//        System.out.println("I am before");
//        Object object = pjp.proceed();
//        Date date1 = new Date();
//        long time = date1.getTime() - date.getTime();
//        System.out.println("I am after");
//        System.out.println("執行了"+time+"毫秒");
//        return object;
//    }
}
