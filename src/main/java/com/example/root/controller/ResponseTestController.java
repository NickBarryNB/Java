package com.example.root.controller;


import com.example.root.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author Nick
 * @Classname ResponseTestController
 * @Date 2021/7/20 17:35
 * @Description
 */
@Controller
public class ResponseTestController {

    @ResponseBody
    @GetMapping("/test/person")
    private Person getPerson(){
        Person person = new Person();
        person.setAge(28);
        //当前时间
        person.setBirth(new Date());
        person.setUserName("zhangsan");
        return person;
    }
}
