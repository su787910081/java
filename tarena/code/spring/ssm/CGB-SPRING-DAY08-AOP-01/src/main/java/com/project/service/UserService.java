package com.project.service;

public interface UserService {
	void saveUser(String user);
	int updateUser(String user);
	Object findUser(Integer id);
	
}
