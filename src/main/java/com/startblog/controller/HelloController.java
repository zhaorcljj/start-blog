package com.startblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaorc
 * @version 1.0, 2022年04月22日
 */
@RestController
@RequestMapping("/test")
public class HelloController {
    @RequestMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }
}
