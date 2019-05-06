package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.HelloService;

// springboot 框架的核心注解
@SpringBootApplication
@Controller
@MapperScan("com.jt.mapper")
public class Starter {
    @Autowired
    private HelloService service;
    
    // springboot 工程 的入口方法，与标准java 入口方法一致
    public static void main(String[] args) {
        // 调用spring 应用的run 方法，将根据自动配置，默认配置，完成初始化的创建，根据依赖完成 所有功能的自动 配置
        SpringApplication.run(Starter.class, args);
    }

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello spring boot, hello 1803" + service.sayHello();
    }
}







