package cn.tedu.demo;

import java.util.Random;
import java.util.Scanner;

public class Demo02 {
	public static void main(String[] args) {
		Demo02 d = new Demo02();
//		d.demo();
//		d.demo(1, 3, 3);
//		d.count();
		d.game();
	}
	
	public void demo(int x, int...y) {
		System.out.print("demo(" + x);
		for (int i = 0; i < y.length; ++i) {
			System.out.print(", " + y[i]);
		}
		System.out.println(")");
	}
	
	public void variableArgument(int...is) {
		
	}
	
	// 将数组的下标作为值，将数组的值作为计数器
	public void count() {
		int[] ary = {1, 1, 4, 6, 3, 4, 5, 6, 6, 0};
		int[] count = new int[ary.length];
		
		for (int i = 0; i < ary.length; ++i) {
			int nIndex = ary[i];
			count[nIndex]++;
		}
		
		for (int i = 0; i < count.length; ++i) {
			if (count[i] != 0) {
				System.out.println(i + " 的个数是：" + count[i]);	
			}
		}
	}
	
	public void func() {
		int[] ary = {0, 0, 1, 0, 1, 0, 0, 1, 1};
		int index = 0;
		for (int i = 0; i < ary.length; ++i) {
			if (ary[i] == 0) {
				ary[i] = 1;
				ary[index++] = 0;
			}
		}
	}
	
	public void game() {
		Random rd = new Random();
		boolean[] arrResult = new boolean[26];
		System.out.print("[");
		for (int i = 0; i < 5; ++i) {
			int index = rd.nextInt(26);
			arrResult[index] = true;
			char ch = (char)(index + 'a');
			System.out.print(ch + " ");
		}
		System.out.println("]");

		System.out.print("请输入五个字母：");
		Scanner input = new Scanner(System.in);
		
		while (true) {
			String str = input.nextLine();
//			str.getChars(srcBegin, srcEnd, dst, dstBegin);
			str.charAt(0);
			System.out.println(str);
			if (str == "exit")
				break;
			
			
		}
		
		input.close();
	}
}
