package cn.tedu.demo;

import java.io.File;

public class DirDemo {
	public static void main(String[] args) {
		demo01();
	}
	
	public static void demo01()
	{
		File file = new File("E:" + File.separator + "su.yunhong");
		System.out.println(file.mkdir());
	}
}
