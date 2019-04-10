package com.project.service.impl;

import org.springframework.stereotype.Component;

import com.project.service.Permission;
import com.project.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	public void saveUser(String user) {
		System.out.println("UserServiceImpl.saveUser(): " + user);
//		throw new RuntimeException();
	}

	@Permission
	public int updateUser(String user) {
		System.out.println("UserServiceImpl.updateUser(): " + user);
		return 0;
	}
	
	public Object findUser(Integer id) {
		System.out.println("UserServiceImpl.findUser()");
		return "suyh";
	}

}
