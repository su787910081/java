package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// 使用下面这个注解表示这个服务是一个EurekaServer 注册中心，否则不能被访问到。
@EnableEurekaServer
public class EurekaCenter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCenter.class, args);
    }
}
