package cn.tedu.datedemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Work {
	public static void main(String[] args) throws ParseException {
		Date today = new Date();
		long todayMs = today.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(today);
		Date nowDay = sdf.parse("2018-09-15 14:52:00");
	}
}
