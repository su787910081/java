package en.tedu.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends ChatProtocol {
	private static ServerSocket ss;
	private List<ClientHandle> ls;
	private BlockingQueue<String> queue;

	public Server() {
		super();
	}

	public Server(Socket socket) throws IOException {
		super(socket);
	}

	public static void main(String[] args) throws IOException {
		// demo01();
		demo02();
	}

	public void init() throws IOException {
		ls = new ArrayList<ClientHandle>();
		ss = new ServerSocket(40000);
		queue = new LinkedBlockingQueue<>(100);

		Forward forward = new Forward();
		forward.setDaemon(true);
		forward.start();

		while (true) {
			Socket socket = ss.accept();

			ClientHandle clientHandle = new ClientHandle(socket);

			synchronized (ls) {
				ls.add(clientHandle);
			}
			new Thread(clientHandle).start();
		}
	}

	public static void demo01() throws IOException {
		ss = new ServerSocket(40000);
		while (true) {
			Socket socket = ss.accept();
			ClientHandler clientThread = new ClientHandler(socket);

			new Thread(clientThread).start();
		}
	}

	public static void demo02() throws IOException {
		Server server = new Server();
		server.init();
	}

	public void forwardToAll(String str) throws IOException {
		// 遍历集合，向集合中的每个元素写数据
		synchronized (ls) {
			Iterator<ClientHandle> it = ls.iterator();
			while (it.hasNext()) {
				ClientHandle handler = it.next();
				handler.sendMessage(str);
			}
		}
	}

	private class Forward extends Thread {
		@Override
		public void run() {
			// 从队列取数据，如果取到，转发给所有客户端
			try {
				while (true) {
					String str = queue.take();
					// 转发给所有用户
					forwardToAll(str);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class ClientHandle extends ChatProtocol implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					String msg = receiveMessage();
					queue.put(msg);
				}
			} catch (IOException | InterruptedException e) {
				System.out.println("有用户下线了！");
			} finally {
				// 将下线的客户从集合移除掉
				synchronized (ls) {
					System.out.println("移除之前：" + ls.size());
					ls.remove(this);
					System.out.println("移除之后：" + ls.size());
				}
			}
		}

		public ClientHandle(Socket socket) throws IOException {
			super(socket);
		}
	}
}
