package com.jt.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-hi")  // 指定调用哪个服务
public interface HiService {
    // value="/hi"  调用服务的URL 地址路径
    @RequestMapping(value="/hi", method=RequestMethod.GET)
    String sayHi(String name);
}
