package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@Configuration // 这个应该是从配置文件中获取信息
public class Server02Controller {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    @ResponseBody
    public String sayHi(String name) {
        return "hi " + name + ", I am from " + port;
    }
}
