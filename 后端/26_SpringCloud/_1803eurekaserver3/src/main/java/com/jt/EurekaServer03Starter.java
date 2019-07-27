package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 作为EurekaClient 服务
@EnableEurekaClient
public class EurekaServer03Starter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer03Starter.class, args);
    }
}
