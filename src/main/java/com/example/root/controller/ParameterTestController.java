package com.example.root.controller;

import com.example.root.bean.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Nick
 * @Classname ParameterTestController
 * @Date 2021/7/19 11:02
 * @Description
 */
@RestController
public class ParameterTestController {

    /**
     * 数据绑定：页面提交的请求数据（GET、POST）都可以和对象属性进行绑定
     * @param person
     * @return
     */
    @PostMapping("/saveuser")
    public Person person(Person person){
        return person;
    }

    @GetMapping("/name")
    public String name(){
        return "XiongKun";
    }

    @GetMapping("/users/{user_id}/pets/{pet_id}")
    public Map<String,Object> user(@PathVariable String user_id,
                                   @PathVariable String pet_id,
                                   @RequestParam Integer age,
                                   @PathVariable Map<String, String> kv){
        Map<String, Object> map = new HashMap<>();

        map.put("age",age);
        map.put("user_id",user_id);
        map.put("pet_id",pet_id);
        map.put("kv",kv);

        return map;
    }

    //1、语法： 请求路径：/cars/sell;low=34;brand=byd,audi,yd
    //2、SpringBoot默认是禁用了矩阵变量的功能
    //      手动开启：原理。对于路径的处理。UrlPathHelper进行解析。
    //              removeSemicolonContent（移除分号内容）支持矩阵变量的
    //3、矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsell(@MatrixVariable Integer low,
                       @MatrixVariable List<String> brand,
                       @PathVariable String path){
        Map<String,Object> map = new HashMap<>();

        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);

        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossAge}/{empAge}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossAge") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empAge") Integer empAge){
        Map<String,Object> map = new HashMap<>();

        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;

    }
}
