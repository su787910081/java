package test.pool;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class TestPool {

	@Test
	public void testPool() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
		Assert.assertNotNull(ctx);

		DruidDataSource dataSource = ctx.getBean("dataSource", DruidDataSource.class);
		Assert.assertNotNull(dataSource);
		System.out.println(dataSource);
		System.out.println("TestPool.testPool()");

		ctx.close();
	}

	@Test
	public void testSessionFactory() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-configs.xml");
		Object sessionFactory = ctx.getBean("sqlSessionFactory");
		System.out.println(sessionFactory);
		Assert.assertNotNull(sessionFactory);
		
		ctx.close();
	}
}
