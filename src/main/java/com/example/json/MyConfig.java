package com.example.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyConfig {
//public class MyConfig implements WebMvcConfigurer {

//    設定攔截器的應用範圍
//    @Autowired
//    private HandlerInterceptor myInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/auth","/auth/register");//應用所有URL路徑
//    }
}
