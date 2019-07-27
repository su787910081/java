package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 作为EurekaClient 服务
@EnableEurekaClient
public class EurekaServer01Starter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer01Starter.class, args);
    }
}
