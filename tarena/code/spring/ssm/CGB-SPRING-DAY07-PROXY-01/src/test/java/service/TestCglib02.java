package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCglib02 {
	@Before
	public void init() {
		System.out.println("TestCglib02.init()");
	}

	@Test
	public void testSaveUser() {
		System.out.println("TestCglib02.testSaveUser()");
	}
	
	@Test
	public void testUpdateUser() {
		System.out.println("TestCglib02.testUpdateUser()");
	}
	
	@After
	public void destroy() {
		System.out.println("TestCglib02.destroy()");
	}
}
