package com.jt.dao;

import java.util.List;

import com.jt.pojo.User;

public interface UserDao {
    List<User> findAll();
}
