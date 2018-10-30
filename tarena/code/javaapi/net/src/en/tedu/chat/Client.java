package en.tedu.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends ChatProtocol {

	public Client() {
		super();
	}

	public Client(Socket socket) throws IOException {
		super(socket);
	}

	public static void main(String[] args) throws IOException {
		// demo01();
		demo02();
	}

	public static void demo01() throws IOException {
		Socket socket = new Socket("localhost", 40000);

		Client client = new Client(socket);

		Scanner sc = new Scanner(System.in);
		System.out.println("请输入内容：");
		while (true) {
			String message = sc.nextLine();
			client.sendMessage(message);
			if ("拜拜".equals(message)) {
				break;
			}
		}
		String str = client.receiveMessage();
		System.out.println(str);

		sc.close();
		client.close();
		socket.close();
	}

	// 发送线程是前台线程，接收线程是后台线程。
	public static void demo02() throws IOException {
		Client client = new Client();
		client.init();
	}

	public void init() throws IOException {
		// 创建socket
		Socket socket = new Socket("localhost", 40000);
		open(socket);
		
		Sender sender = new Sender();
		Receive receive = new Receive();
		receive.setDaemon(true);
		
		sender.start();
		receive.start();
//		socket.close();
	}

	// 发送数据的线程类
	private class Sender extends Thread {
		@Override
		public void run() {
			// 用户从控制台输入数据，发送
			Scanner sc = new Scanner(System.in);
			System.out.println("开始聊天：");
			try {
				while (true) {
					String message = sc.nextLine();
					sendMessage(message);
					if ("明天见".equals(message)) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				sc.close();
			}
		}
	}

	private class Receive extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String str = receiveMessage();
					System.out.println(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
