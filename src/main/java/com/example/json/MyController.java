package com.example.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    @Qualifier("hpPrinter")
    private Printer printer;

    @GetMapping("/test1")
    public String test1(){
        System.out.println("執行 test1 方法");
        return "Hello test1";
//        throw new RuntimeException("test1 error");
    }
    @GetMapping("/test2")
    public String test2(){
        System.out.println("執行 test2 方法");
        return "Hello test2";
//        throw new IllegalArgumentException("test2 error");
    }
}
