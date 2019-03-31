package cn.tedu.project.dao.impl;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import cn.tedu.project.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	
	@Override
	public void insertUser(String user) {
		System.out.println("dao.insertUser");
	}

}
