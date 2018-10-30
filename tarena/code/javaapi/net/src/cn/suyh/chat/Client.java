package cn.suyh.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private ChatSession sess;

	public Client() {
		super();
	}

	// 启动客户端
	public void start() {
		sess = new ChatSession();

		try {
			Socket socket = new Socket("localhost", 40000);
			sess.open(socket);
		} catch (IOException e) {
			System.out.println("连接失败");
			// e.printStackTrace();
		}

		Sender sender = new Sender();
		Recevie recevie = new Recevie();
		recevie.setDaemon(true);
		recevie.start();
		sender.start();
	}

	private class Sender extends Thread {
		@Override
		public void run() {
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入：");
			try {
				while (true) {
					String msg = sc.nextLine();
					sess.send(msg);
				}
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				sc.close();
			}
		}
	}

	private class Recevie extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String msg;
					msg = sess.recevie();
					System.out.println(msg);
					if ("byebye".equals(msg)) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
