package com.jt.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HiService {
    @Autowired
    private RestTemplate template;

    // 使用注解完成熔断器的引入，一旦引入会将异常或者问题转向收集问题的处理器(方法)
    // 当前方法的问题，会通过hystrix 转向一个方法的调用 errors。方法里的逻辑就是处理各种问题的逻辑
    @HystrixCommand(fallbackMethod = "errors")
    public String sayHi(String name) {
        // 人工抛问题，在访问负载均衡时，手动宕机一个服务
        String hi = template.getForObject("http://service-hi/hi?name=" + name, String.class);
        return hi;
    }

    // 转向的同时将服务未处理的参数交给fallback批=指向的方法
    public String errors(String name) {
        return "hi, " + name + ", sorry, error happens";
    }


}
