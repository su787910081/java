package beans;

import java.util.Date;

import factory.ApplicationContext;

public class TestFactoryDemo01 {
	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext("beans.xml");
		Date date3 = (Date) app.getBean("date1");
		
		System.out.println(date3);
	}
}
