package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.User;

// RestController 是组合注解，组合和ResponseBody 和Controller
@RestController
public class Demo01Controller {
    

    // 返回user 对象
    @RequestMapping("getUser")
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("张三");
        
        return user;
    }
    
    // 完成前端传送的参数接收，封装返回
    @RequestMapping("setUser")
    public User setUser(String name, Integer id, Integer age) {
        System.out.println(name + ", " + id + ", " + age);

        User user = new User();
        user.setAge(age);
        user.setId(id);
        user.setName(name);

        return user;
    }
}
