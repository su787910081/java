package cn.tedu.demo;

import java.util.Scanner;

public class Demo02 {
	public static void main(String[] args) {
		int score = 99;
		if (score >= 99) {
			System.out.println("A");
		} else if (score >= 80) {
			System.out.println("B");
		} else if (score >= 60) {
			System.out.println("C");
		} else {
			System.out.println("D");
		}
		
		Scanner input = new Scanner(System.in);
		System.out.print("请输入商品单价: ");
		int singlePrice = input.nextInt();
		System.out.print("请输入购买的数量：");
		int count = input.nextInt();
		// 计算应付金额
		int total = singlePrice * count;
		System.out.print("应收金额为：" + total + "，请输入实付金额：");
		int pay = input.nextInt();
		
		// 如果pay < total，错误，如果pay >= total 
		if (pay < total) {
			System.out.println("付款失败！");
		} else {
			int minus = pay - total;
			System.out.println("付款成功，找零：￥" + minus );
		}

		input.close();
	}
}
