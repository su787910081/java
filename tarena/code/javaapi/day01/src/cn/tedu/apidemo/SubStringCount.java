package cn.tedu.apidemo;

import java.text.DecimalFormat;

public class SubStringCount {
	public static void main(String[] args) {
		String str = "you can you up, I can I do, no can no bb.";
		
		String dest = "dod";
		int count = 0;
		for (int i = 0; i < str.length(); i += dest.length()) {
			i = str.indexOf(dest, i);
			if (i == -1) 
				break;

			++count;
		}
		
		System.out.println(count);
		
		String str1 = "上海自来水来自海上";
		
		boolean b = true;
		for (int i = 0, j = str1.length() - 1; i < j; ++i, --j) {
			if (str1.charAt(i) != str1.charAt(j)) {
				b = false;
				break;
			}
		}

		System.out.println(8.50);
		
		str = String.valueOf(b);
		System.out.println(str);
		
		DecimalFormat df = new DecimalFormat("00.00");
		System.out.println(df.format(118.5));
	}
}
