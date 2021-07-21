package com.example.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nick
 * @Classname RequestController
 * @Date 2021/7/19 15:40
 * @Description
 */

/**
 * 此处如果是@RestController的话，return的值会自动转义成字符串，不会变成跳转请求
 */
@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){

        request.setAttribute("msg","It Worked...");
        request.setAttribute("code",200);

        return "forward:/success";  //转发到   success请求
    }

    //测试map、model、request、response的参数
    @GetMapping("/params")
    public String testParam(Map<String,Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        map.put("hello","world");
        model.addAttribute("world","hello");
        request.setAttribute("message","helloWorld");
        Cookie cookie = new Cookie("k1","v1");
        response.addCookie(cookie);

        return "forward:/success";
    }
    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute(value = "msg",required = false) String msg,
                       @RequestAttribute(value = "code",required = false) Integer code,
                       HttpServletRequest request){
        Object msg1 = request.getAttribute("msg");

        Map<String, Object> map = new HashMap<>();

        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        map.put("annotation_msg",msg);  //注解返回的msg值
        map.put("reqMethod_msg",msg1);  //request返回的msg值
        map.put("code",code);
        map.put("hello",hello);
        map.put("world",world);
        map.put("message",message);

        return map;
    }

}
