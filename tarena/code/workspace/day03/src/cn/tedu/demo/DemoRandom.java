package cn.tedu.demo;

import java.util.Random;
import java.util.Scanner;

public class DemoRandom {
	public static void main(String[] args) {
//		mathRandom();
//		rd();
		func();
	}
	
	public static void mathRandom()
	{
		double dRd = Math.random();
		int m = (int)dRd;
		m = m * 1000 + 1;
	}
	
	public static void rd()
	{
		Random random = new Random();
		int m = random.nextInt(1000);
		System.out.println(m);
	}
	
	public static void func()
	{
		int guess = 0;
		int standard = 0;
		Random rd = new Random();
		Scanner input = new Scanner(System.in);
		standard = rd.nextInt(1000);
		
		System.out.println("请输入一个数字: ");
		while (true) {
			guess = input.nextInt();
			if (guess == 0) {
				System.out.println("欢迎下次使用！再见！");
				break;
			}

			if (guess == standard) {
				System.out.println("恭喜你，猜对了！");
			} else if (guess < standard) {
				System.out.print("小了.");
			} else {
				System.out.print("大了.");
			}
		}
		
		input.close();
	}
}
