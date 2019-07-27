package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {
    // 这个正常应该是在service 中注入
    @Autowired
    private RestTemplate template;

    @RequestMapping("/hi")
    @ResponseBody
    public String hello(String name) {
        // 利用Template 对象访问服务
        // 参数1(url): 是服务连接地址
        // http://service-hi 对应了后台服务提供者的工程(service-hi 是在注册中心注册的服务名)
        String hi = template.getForObject("http://service-hi/hi?name=" + name, String.class);
        return hi;
    }
}
