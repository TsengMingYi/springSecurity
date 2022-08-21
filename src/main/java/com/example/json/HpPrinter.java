package com.example.json;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HpPrinter implements Printer{

    @Value("${printer.count:20}")
    private Integer number;

    @Override
    public void print(String message) {
        number--;
        System.out.println("hp印表機："+message);
        System.out.println("hp印表機："+number);
    }

//    @PostConstruct
//    public void init(){
//        number = 10;
//    }
}
