package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 作为EurekaClient 服务
@EnableEurekaClient
public class EurekaServer02Starter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer02Starter.class, args);
    }
}
