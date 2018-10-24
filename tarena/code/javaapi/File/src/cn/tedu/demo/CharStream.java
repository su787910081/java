package cn.tedu.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharStream {
	public static void main(String[] args) throws IOException {
		demo01();
		demo02();
	}

	public static void demo01() throws IOException {
		FileOutputStream fos = new FileOutputStream("osw.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		
		osw.write("转换流来了.");
		
		osw.close();
		fos.close();
	}
	
	public static void demo02() throws IOException {
		FileInputStream fis = new FileInputStream("osw.txt");
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

		char[] cbuf = new char[4];
		int nlen = isr.read(cbuf);
		System.out.println(nlen);
		System.out.println(new String(cbuf, 0, nlen));
		isr.close();
		fis.close();
	}
}
