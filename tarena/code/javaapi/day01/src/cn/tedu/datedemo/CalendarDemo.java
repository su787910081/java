package cn.tedu.datedemo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarDemo {
	public static void main(String[] args) {
		/*
		 * 得到Calendar 对象有两种方式
		 */
		Calendar calendar = new GregorianCalendar();
		
		// 方式二: 使用静态方法(常用方式)
		Calendar cal = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		System.out.println(year);
		
		int month = calendar.get(Calendar.MONTH);
		System.out.println(month);
		
		demo01();
	}
	
	public static void demo01() {
		// 获取当前日期的年月日的分量
		Calendar calendar = Calendar.getInstance();
		// 获取时间分量
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		
		System.out.println(year + "-" + month + "-" + day);
		
		Date d = calendar.getTime();
		System.out.println(d);
	}
}





