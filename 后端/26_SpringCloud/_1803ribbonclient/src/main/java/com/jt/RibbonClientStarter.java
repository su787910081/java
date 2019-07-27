package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class RibbonClientStarter {
    public static void main(String[] args) {
        SpringApplication.run(RibbonClientStarter.class, args);
    }

    // ribbon 组件配合一个对象RestTemplate
    // 利用RestTemplate 调用服务
    @Bean   // 其他地方可以注入使用
    @LoadBalanced   // 负载均衡模式：轮循
    public RestTemplate getResouce() {
        return new RestTemplate();
    }
}
