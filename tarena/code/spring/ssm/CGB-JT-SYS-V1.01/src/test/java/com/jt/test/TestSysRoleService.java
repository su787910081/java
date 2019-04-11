package com.jt.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.sys.pojo.SysRole;
import com.jt.sys.service.SysRoleService;

public class TestSysRoleService {
	private ClassPathXmlApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
	}
	
	@Test
	public void testSaveObject() {
		SysRoleService sysRoleService = ctx.getBean("sysRoleServiceImpl", SysRoleService.class);
		SysRole entity = new SysRole();
		entity.setName("K");
		entity.setNote("KKKK");
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		
		int res = sysRoleService.saveObject(entity);
		Assert.assertEquals(1, res);
	}
	
	@After
	public void destroy() {
		ctx.close();
	}
}
