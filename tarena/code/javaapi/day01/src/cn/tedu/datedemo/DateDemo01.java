package cn.tedu.datedemo;

import java.util.Date;

public class DateDemo01 {
	public static void main(String[] args) {
		long sysMills = System.currentTimeMillis();
		Date date = new Date();
		System.out.println(date);
		long currentMills = date.getTime();
		System.out.println(currentMills);
		System.out.println(sysMills);
		
		long tomorrow = currentMills + 24 * 60 * 60 * 1000;
		Date toDate = new Date(tomorrow);
		System.out.println(toDate);
		
		long yestoday = currentMills - 24 * 60 * 60 * 1000;
		date.setTime(yestoday);
		System.out.println(date);
	}
}




