package test.unit;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.sys.pojo.SysRole;
import com.jt.sys.service.SysRoleService;

public class TestRoleService {

	private ClassPathXmlApplicationContext ctx;
	
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
	}
	
	@Test
	public void testFindPageObjects() {
		SysRoleService service = ctx.getBean("sysRoleServiceImpl", SysRoleService.class);
		List<SysRole> list = service.findPageObjects();
		System.out.println("TestRoleService.testFindPageObjects()");
		System.out.println(list);
	}
	
	@After
	public void destroy() {
		ctx.close();
	}
}
