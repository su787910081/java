package en.tedu.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatProtocol {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream os;

	public ChatProtocol() {
		super();
	}

	public ChatProtocol(Socket socket) throws IOException {
		open(socket);
	}

	public void open(Socket socket) throws IOException {
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		os = new DataOutputStream(socket.getOutputStream());
	}
	public void close() {
		try {
			in.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String receiveMessage() throws IOException {
		int type = in.readInt();
		if (type == 1) {

		}

		int length = in.readInt();
		byte[] bys = new byte[length];
		int len = in.read(bys);
		String content = new String(bys, "UTF-8");
		return content;
	}

	public void sendMessage(String info) throws IOException {
		os.writeInt(1);
		byte[] bys = info.getBytes("UTF-8");
		os.writeInt(bys.length);
		os.write(bys);
		os.flush();
	}
}
