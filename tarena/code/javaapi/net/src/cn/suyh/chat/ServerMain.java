package cn.suyh.chat;

public class ServerMain {
	public static void main(String[] args) {
		Server server = new Server();
		server.init();
		server.start();
	}
}
