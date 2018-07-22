package cn.tedu.demo;

import java.util.Arrays;
import java.util.Random;

public class JavaArray {
	public static void main(String[] args) {
//		firstFunc();
//		secondFunc();
//		arrayCopy();
//		thirdFunc();
//		fourthFunc();
//		fifthFunc();
//		sixthFunc();
		seventhFunc();
	}
	
	public static void firstFunc() {
		int[] ary = new int[2];
		System.out.println(ary[0]);
		System.out.println(ary[1]);
		
		int[] ary4 = null;
		if (ary4 == null)
			System.out.println("ary4 is null");
		System.out.println("ary.length: " + ary.length);
//		ary[4] = 1;
//		System.out.println(ary[2]);
//		int ary3[];
//		int[10] ary2;
//		int[] arr = new int[]{ 10, 20 };	// æ≤Ã¨≥ı ºªØ
		Arrays.sort(ary);
		
		String[] ary2 = {"d", "a", "c", "b"};
		Arrays.sort(ary2);
		for (String s : ary2) {
			System.out.print(s + " ");
		}
		System.out.println("");
		
		System.out.println(Arrays.toString(ary2));
		System.out.println(ary2);
		
	}
	
	public static void secondFunc() {
		int[] ary1 = {1, 2, 3};
		int[] ary2 = Arrays.copyOf(ary1, 5);
				
		System.out.println(Arrays.toString(ary2));
		
		ary1 = Arrays.copyOf(ary1, 5);
		System.out.println(Arrays.toString(ary1));
//		int[] ar = Arrays.copyOf(arr, 3);
	}

	public static void arrayCopy() {
		int[] ary1 = {1, 2, 4, 5, 6, 7, 8};
		int[] ary2 = new int[4];
		System.arraycopy(ary1, 1, ary2, 0, ary2.length);
		System.out.println(Arrays.toString(ary2));
	}

	public static void thirdFunc() {
		int[] arr1 = {3, 5, 2, 18, 9, 3};
		int[] arr2 = Arrays.copyOf(arr1, arr1.length + 1);
		Arrays.sort(arr1);
		arr2[arr1.length] = arr1[arr1.length - 1];
		System.out.println(Arrays.toString(arr2));
	}

	public static void fourthFunc() {
		Random rd = new Random();
		int[] arr1 = new int[10];
		for (int i = 0; i < arr1.length; ++i) {
			arr1[i] = rd.nextInt(100);
		}
		System.out.println(Arrays.toString(arr1));
		int[] arr2 = arr1;
//		arr1 = Arrays.copyOf(arr1, arr1.length + 1);
		arr1 = new int[arr1.length + 1];
		System.arraycopy(arr2, 0, arr1, 1, arr2.length);
		Arrays.sort(arr2);
		arr1[0] = arr2[0];
		System.out.println(Arrays.toString(arr1));
	}
	
	public static void fifthFunc() {
		int[] ary = new int[7];
		Random rd = new Random();
		for (int i = 0; i < ary.length; ++i) {
			ary[i] = rd.nextInt(100);
		}
		
		int max = ary[0];
		for (int i = 1; i < ary.length; ++i) {
			if (max < ary[i]) {
				max = ary[i];
			}
		}
		ary = Arrays.copyOf(ary, ary.length + 1);
		ary[ary.length - 1] = max;
		System.out.println(Arrays.toString(ary));
	}
	
	public static void sixthFunc() {
		Random rd = new Random();
		int[] arr1 = new int[10];
		for (int i = 0; i < arr1.length; ++i) {
			arr1[i] = rd.nextInt(100);
		}
		int min = arr1[0];
		for (int i = 1; i < arr1.length; ++i) {
			if (arr1[i] < min) {
				min = arr1[i];
			}
		}

		int[] arr2 = new int[arr1.length + 1];
		System.arraycopy(arr1, 0, arr2, 1, arr1.length);
		arr2[0] = min;
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
	}
	
	public static void seventhFunc() {
		int[][] arr1 = {{1, 1, 1}, {2, 2}, {3, 3, 3, 3}};
		for (int i = 0; i < arr1.length; ++i) {
			System.out.println("arr1[" + i + "].length = " + arr1[i].length);
			
			for (int j = 0; j <arr1[i].length; ++j) {
				System.out.println("arr1[" + i + "][" + j + "] = " + arr1[i][j]);
			}
			
		}
	}
	
}
