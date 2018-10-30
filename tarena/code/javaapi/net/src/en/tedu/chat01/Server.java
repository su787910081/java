package en.tedu.chat01;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static ServerSocket ss;
	
	public static void main(String[] args) throws IOException {
		demo01();
	}
	
	public static void demo01() throws IOException {
		ss = new ServerSocket(40000);
		
		Socket socket = ss.accept();
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		int type = dis.readInt();
		if (type == 1)
		{
			System.out.println("文本类型！");
		}
		
		int len = dis.readInt();
		System.out.println("数据长度: " + len);
		byte[] bys = new byte[len];
		
		int length = dis.read(bys);
		System.out.println("读取到的数据长度: " + length);
		
		String str = new String(bys, "UTF-8");
		System.out.println("读取到的实际文本信息: " + str);		
		
		dis.close();
		is.close();
		socket.close();
	}
}
