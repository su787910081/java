package en.tedu.chat;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends ChatProtocol implements Runnable {
	private Socket socket;

	public ClientHandler(Socket socket) throws IOException {
		super(socket);
		this.socket = socket;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String msg = receiveMessage();
				if ("拜拜".equals(msg)) {
					System.out.println(socket.getPort() + " 已下线");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			sendMessage("您已下线");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
