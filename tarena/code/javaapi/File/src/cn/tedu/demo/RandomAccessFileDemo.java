package cn.tedu.demo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws IOException {
		// demo01();
		demo02();
	}

	private static void demo01() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("a.txt", "rw");
		raf.write(97);
		raf.write("hello world!".getBytes());

		raf.seek(0);

		byte[] btRead = new byte[100];
		int nLen = raf.read(btRead);
		System.out.println(nLen);
		System.out.println(new String(btRead));

		raf.close();
	}

	public static void demo02() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("b.txt", "rw");
		raf.write("hello �й��ˣ�".getBytes());

		raf.seek(0);

		byte[] bys = new byte[1024];
		int len  = 0;
		while ((len = raf.read(bys)) > 0) {
			System.out.print(new String(bys, 0, len));
		}

		raf.close();
	}
}