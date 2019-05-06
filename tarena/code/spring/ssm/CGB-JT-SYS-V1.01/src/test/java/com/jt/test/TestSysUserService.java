package com.jt.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.common.vo.PageObject;
import com.jt.sys.pojo.SysUser;
import com.jt.sys.service.SysUserService;

public class TestSysUserService {
	private ClassPathXmlApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
		Assert.assertNotNull(ctx);
	}
	
	@Test
	public void testFindPageObjects() {
		System.out.println("TestSysUserService.testFindPageObjects()");
		SysUserService service = ctx.getBean("sysUserServiceImpl", SysUserService.class);
		
		Assert.assertNotNull(service);
		PageObject<SysUser> pou = service.findPageObjects(null, 1);
		System.out.println("pou: " + pou);
	}
	
	@After
	public void destroy() {
		ctx.close();
	}
}
