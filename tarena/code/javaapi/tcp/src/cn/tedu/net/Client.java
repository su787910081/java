package cn.tedu.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {
		demo02();
	}

	public static void demo01() throws IOException {
		// 向服务器发起连接请求
		Socket socket = new Socket("localhost", 10086);
		System.out.println(socket);
		// 获取socket 中的输出流对象
		OutputStream os = socket.getOutputStream();
		os.write("hello".getBytes("UTF-8"));

		os.close();
		socket.close();
	}

	public static void demo02() throws IOException {
		// 向服务器发起连接请求
		Socket socket = new Socket("localhost", 10086);
		System.out.println(socket);
		// 获取socket 中的输出流对象
		OutputStream os = socket.getOutputStream();
		
		DataOutputStream dos = new DataOutputStream(os);
		
		dos.writeInt(1);
		String str = "hello，Socket编程";
		byte[] bys = str.getBytes("UTF-8");
		dos.writeLong(bys.length);
		
		// 发送数据
//		dos.writeBytes(str);
		dos.write(bys);
		
		dos.close();
		os.close();
		socket.close();
	}
}
