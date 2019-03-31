package ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutoWire01 {
	private static ClassPathXmlApplicationContext ctx;
	
	static void init() {
		ctx  = new ClassPathXmlApplicationContext("ioc.xml");
	}
	
	static void close() {
		ctx.close();
	}
	
	public static void main(String[] args) {
		init();
		
		JdbcTemplate jt = ctx.getBean("jdbcTempate", JdbcTemplate.class);
		System.out.println(jt.getDataSource());
		
		close();
	}
}
