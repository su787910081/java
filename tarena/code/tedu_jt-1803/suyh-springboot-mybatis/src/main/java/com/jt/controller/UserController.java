package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.dao.UserDao;
import com.jt.pojo.User;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @RequestMapping("findUser")
    public List<User> findUser() {
        return userDao.findAll();
    }
}
