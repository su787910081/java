package beans;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc.xml");
		
		DataSource ds1 = ctx.getBean("dataSource1", DataSource.class);
		System.out.println(ds1);
		
		DataSource ds2 = ctx.getBean("dataSource2", DataSource.class);
		System.out.println(ds2);
		
		JdbcTemplate tp = ctx.getBean("jdbcTemplate3", JdbcTemplate.class);
		System.out.println(tp);
		
		
		ctx.close();
	}
}
