package en.tedu.chat01;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		demo01();
	}
	
	public static void demo01() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 40000);
		
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		dos.writeInt(1);
		String str = "¡ƒÃÏ “";
		byte[] bys = str.getBytes("UTF-8");
		dos.writeInt(bys.length);
		dos.write(bys);		
		
		dos.close();
		os.close();
		socket.close();
	}
}
