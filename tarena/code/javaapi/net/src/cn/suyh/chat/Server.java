package cn.suyh.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
	private ServerSocket ss;
	private List<ChatSession> lsSession;

	public void init() {
		try {
			ss = new ServerSocket(40000);
			lsSession = new ArrayList<ChatSession>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		if (ss == null)
			return;

		synchronized (lsSession) {
			if (lsSession == null)
				return;
		}

		try {
			Socket socket = ss.accept();
			ChatSession session = new ChatSession();
			session.open(socket);

			synchronized (lsSession) {
				lsSession.add(session);
			}

			ClientReceive receive = new ClientReceive(session);
			receive.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void forwardMsg(String msg) throws IOException {
		synchronized (lsSession) {
			Iterator<ChatSession> iter = lsSession.iterator();
			while (iter.hasNext()) {
				iter.next().send(msg);
			}
		}
	}

	// 每个线程是一个客户端连接，每个线程只处理数据的接收
	private class ClientReceive extends Thread {
		ChatSession session;

		public ClientReceive(ChatSession session) {
			super();
			this.session = session;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String msg = session.recevie();
					forwardMsg(msg);
					if ("byebye".equals(msg)) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				synchronized (lsSession) {
					System.out.println("删除前：" + lsSession.size());
					lsSession.remove(session);
					System.out.println("删除后：" + lsSession.size());
				}
			}
		}
	}

}
