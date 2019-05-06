package com.jt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jt.pojo.User;

// JPA 的API 中有一个repository 持久层接口，在继承时利用jar 包依赖
// 两个泛型，
// 第一个泛型定义 的是当前dao 接口对应的pojo 类；
// 第二个泛型表示当前pojo 使用的序列化ID 的类型
public interface UserDao extends JpaRepository<User, Integer> {

}
