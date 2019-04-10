package com.project.sys.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.project.sys.dao.SysUserDao;
import com.project.sys.entity.SysUser;
import com.project.sys.vo.Account;

public class TestSysUserDao01 {
	private SqlSessionFactory factor;

	@Before
	public void init() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		factor = new SqlSessionFactoryBuilder().build(is);
		
		
	}
	
	@Test
	public void testInit() {
		Assert.assertNotNull(factor);
		SqlSession session = factor.openSession();
		SysUserDao dao = session.getMapper(SysUserDao.class);
		List<Account> list = dao.findNameAndPwd();
		
		System.out.println(list);
		session.close();
	}
	
	@Test
	public void testInsertObject() {
		SqlSession session = factor.openSession();
		SysUserDao dao = session.getMapper(SysUserDao.class);
		
		SysUser user = new SysUser();
		user.setUsername("tmooc");
		user.setPassword("123456");
		user.setPhone("177");
		int rows = dao.insertObject(user);
		System.out.println("rows=" + rows);
		System.out.println("user=" + user);
		session.commit();
		session.close();
	}
	
	@Test
	public void testFindUsers() {
		SqlSession session = factor.openSession();
		SysUserDao dao = session.getMapper(SysUserDao.class);
		List<SysUser> list = dao.findUsers("username", "139");
		System.out.println(list);
		
		session.close();
	}
}
