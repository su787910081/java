package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.dao.UserDao;
import com.jt.pojo.User;

@RestController
public class Demo02Controller {

    // jpa 案例，需要注入JPA 持久层接口的实例对象
    @Autowired
    private UserDao userDao;
    
    @RequestMapping("findAll")
    public List<User> findAll() {
        List<User> list = userDao.findAll();
        
        return list;
    }
}
