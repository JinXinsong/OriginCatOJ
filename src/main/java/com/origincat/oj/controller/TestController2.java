package com.origincat.oj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

    @RequestMapping(value = "/gettest")
    public String gettest(){
        return "hello,world";
    }
}
