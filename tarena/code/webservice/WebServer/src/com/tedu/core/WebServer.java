package com.tedu.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tedu.common.ServerContext;

// 基于HTTP 协议的WEB 服务端程序
public class WebServer {
	public static void main(String[] args) {
		WebServer svr = new WebServer();
		svr.start();
	}
	
	
	
	private ServerSocket server;
	private ExecutorService threadPool;
	
	public WebServer() {
		try {
			// 初始化，监听8080 端口
			server = new ServerSocket(ServerContext.port);
			threadPool = Executors.newFixedThreadPool(ServerContext.maxThread);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务器启动失败...");
		}
	}
	
	//启动服务端接收客户端的请求并处理
	private void start() {
		try {
			while (true) {
				System.out.println("等待客户端连接");
				Socket socket = server.accept();
				
				// 启动线程处理客户端 的请求
				threadPool.execute(new ClientHandler(socket));
				
				
				// 处理请求(向客户端响应一个字符串)
//				OutputStream out = socket.getOutputStream();
//				out.write("hello 1790".getBytes());
//				out.flush();
//				System.out.println("hello 1790");
				
				
//				
//				String data = "hello 1709...";
//				// PrintStream 用于包装其他输出流，提供更多便捷的功能
//				OutputStream out = socket.getOutputStream();
//				PrintStream ps = new PrintStream(out);
//				ps.println("HTTP/1.1 200 OK");
//				ps.println("Content-Type:text/html");
//				ps.println("Content-Length:" + data.length());
//				ps.println(""); // 空行
//				ps.write(data.getBytes());
//				ps.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


