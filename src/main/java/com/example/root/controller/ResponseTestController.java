package com.example.root.controller;


import com.example.root.bean.Person;
import org.springframework.core.io.FileSystemResource;
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

    @ResponseBody   //@ResponseBody的内容会被RequestResponseBodyMethodProcessor处理--> 调用各种messageConverter
    @GetMapping("res")
    public FileSystemResource file(){

        //文件是以这样的方式返回，看是谁处理的（messageConverter）
        return null;
    }

    @ResponseBody   //利用返回值处理器里面的消息转换器converter进行处理
    //test 是否能返回xml类型  需要引入xml依赖包       undone！！！   ***, produces = {"test/person;charset=UTF-8"}
    @GetMapping(path = "/test/person")
    private Person getPerson(){
        Person person = new Person();
        person.setAge(28);
        //当前时间
        person.setBirth(new Date());
        person.setUserName("zhangsan");
        return person;
    }
}
