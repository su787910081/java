package cn.tedu.project.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.tedu.project.dao.UserDao;
import cn.tedu.project.service.UserService;

/**
 * 写一个测试类: class TestUserService
 * 1). 在测试类中从spring 容器中获取UserServiceImpl 类型的对象
 * 2). 获得此对象后，调用此对象的方法saveUser() 
 * 根据以上需求，编写TestUserService
 * @author suyh
 *
 */

@Service
public class UserServiceImpl implements UserService {
//	@Qualifier("userDaoImpl")
//	@Autowired
	@Resource
	private UserDao userDao;
	
//	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		System.out.println("UserServiceImpl.setUserDao()");
	}

//	@Autowired
	public UserServiceImpl(UserDao ud) {
		this.userDao = ud;
	}
	@Override
	public void saveUser(String user) {
		userDao.insertUser(user);
	}

}
