package com.example.root.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Nick
 * @Classname HelloController
 * @Date 2021/7/17 0:17
 * @Description
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String Hello(){
        return "hello";
    }
}
