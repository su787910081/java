package com.tedu.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.ws.ResponseWrapper;

import com.tedu.common.HttpContext;
import com.tedu.common.ServerContext;
import com.tedu.http.HttpRequest;
import com.tedu.http.HttpResponse;
import com.tedu.jdbc.JdbcUtils;

public class ClientHandler implements Runnable {
	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			/*
			 * 创建HttpRequest 对象，
			 *  用于封闭浏览器发送的请求信息
			 */
			HttpRequest request = new HttpRequest(in);
						
			// 解析请求信息中的第一行(请求行信息)
			// GET /index.html HTTP/1.1
			if (request.getUri() != null) {
				
				/*
				 * 创建HttpResponse 对象，用于封装
				 *  Http 响应信息
				 */
				HttpResponse response = new HttpResponse(out);
	
				/* 如果uri 是以/Regist 开头，表明是注册请求
				 * 如果uri 是以/Login 开头，表明是登录请求
				 */
				if (request.getUri().startsWith("/Regist") ||
						request.getUri().startsWith("/Login")) {// 注册或登录请求
					service(request, response);
					return;
				}
				
				
				File file = new File(ServerContext.webRoot
						+ request.getUri());
				
				int statusCode = HttpContext.STATUS_CODE_OK;
				/*
				 * 检查浏览器请求的资源是否存在，如果不存在
				 *  响应404 页面，提示资源找不到。
				 */
				if (!file.exists()) { // 响应404 页面
					file = new File(ServerContext.webRoot + "/" + ServerContext.notFoundPage);
					// 设置状态码为404
					statusCode = HttpContext.STATUS_CODE_NOT_FOUND;
				}
				
			
				// 对response 对象中的属性进行初始化
				response.setProtocol(
						request.getProtocol());// 协议
				response.setStatus(statusCode);// 状态码
				response.setContentType(getContentTypeByFile(file));// 数据类型
				response.setContentLength(
						(int)file.length());// 数据长度
				
				responseFile(response, file);
			}

			socket.close();
			System.out.println("响应客户端完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理注册或者登录请求
	 * @param request
	 * @param response
	 */
	private void service(HttpRequest request, HttpResponse response) {
		if (request.getUri().startsWith("/Regist")) {
			System.out.println("处理注册请求...");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println("username: " + username);
			System.out.println("password: " + password);
			
			// 通过JDBC 技术将用户名和密码保存到数据库中
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				conn = JdbcUtils.getConnection();
				String sql = "insert into user values(null, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.executeUpdate();
				
				// 响应浏览器(提示用户注册成功)
				File file = new File(
						ServerContext.webRoot + "/reg_success.html");
				response.setProtocol(ServerContext.protocol);
				response.setStatus(HttpContext.STATUS_CODE_OK);
				response.setContentType(getContentTypeByFile(file));
				response.setContentLength((int)file.length());
				responseFile(response, file);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtils.close(conn, ps, rs);
			}
		} else {
			// TODO: 处理登录请求
			System.out.println("处理登录请求...");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println("username: " + username);
			System.out.println("password: " + password);
			
			// 通过用户名和密码查询是否存在
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				conn = JdbcUtils.getConnection();
				String sql = "select * from user where username=? and password=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if (rs.next()) {// 提示登录成功
					// 响应浏览器(提示用户注册成功)
					File file = new File(
							ServerContext.webRoot + "/log_success.html");
					response.setProtocol(ServerContext.protocol);
					response.setStatus(HttpContext.STATUS_CODE_OK);
					response.setContentType(getContentTypeByFile(file));
					response.setContentLength((int)file.length());
					responseFile(response, file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtils.close(conn, ps, rs);
			}
		}
	}

	/**
	 * 根据文件的后缀名获取对应的响应数据类型
	 * @param file
	 * @return
	 */
	public String getContentTypeByFile(File file) {
		/* 思路: 
		 * 1. 获取文件的名字，如: index.html
		 * 2. 根据文件名进行截取，获取后缀名 如: html
		 * 3. 根据后缀名到types 中获取 响应数据的类型并返回
		 */
		
		// 1. 获取文件的名字
		String fileName = file.getName();
		// 2. 根据文件名进行截取，获取后缀名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 3. 根据后缀名到types 中获取响应数据类型
		String type = ServerContext.types.get(ext);
		System.out.println(ext + ": " + type);
		return type;
	}
	
	/**
	 * 将指定文件响应给客户端
	 * @param response
	 * @param file
	 * @throws IOException
	 */
	public void responseFile(HttpResponse response, File file) throws IOException {
		PrintStream ps = new PrintStream(response.getOutputStream());

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		byte[] buff = new byte[(int) file.length()];
		bis.read(buff);
		ps.write(buff);
	}
}
