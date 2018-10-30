package cn.suyh.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatSession {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public ChatSession(Socket socket) throws IOException {
		super();
		open(socket);
	}

	public ChatSession() {
		super();
	}

	public void open(Socket socket) throws IOException {
		this.socket = socket;
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void close() throws IOException {
		synchronized (dos) {
			dos.close();
		}
		synchronized (dis) {
			dis.close();
		}

		socket.close();
	}

	public void send(String msg) throws IOException {
		byte[] bys = msg.getBytes("UTF-8");

		synchronized (dos) {
			dos.writeInt(1);
			dos.writeInt(bys.length);
			dos.write(bys);
		}
	}

	public String recevie() throws IOException {
		byte[] bys;
		synchronized (dis) {
			int type = dis.readInt();
			if (type == 1) {
				// do nothing
			}

			int len = dis.readInt();
			bys = new byte[len];
			dis.read(bys);
		}

		String msg = new String(bys, "UTF-8");
		return msg;
	}
}
