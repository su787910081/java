package com.tedu.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 useOutputStream(request, response);
	    
//	    useWriter(request, response);
	}
	
	private void useOutputStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    // response.getWriter().append("Served at: ").append(request.getContextPath());
        // 1. 使用字节流向客户端发送数据   getOutputStream
        // 1). 发送英文数据
        response.getOutputStream().write("hello..response.<br />".getBytes());
        // 2). 发送中文数据
        // >> 明确指定发送数据时的编码为UTF-8
        response.getOutputStream().write("哈喽..response".getBytes("utf-8"));
        // >> 明确指定浏览器也使用UTF-8　来接收服务器数据
        // response.setHeader("Content-Type", "text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
	}
	
	private void useWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    // 2. 使用字符流向客户端 发送数据   getWriter
	    // >> 通知服务器使用 UTF-8 来发送数据
	    // response.setCharacterEncoding("utf-8");
        // 1). 发送英文数据
	     response.getWriter().write("hello..response<br />");
        // 2). 发送中文数据
	    
	    
	    
	    
	    response.getWriter().write("哈喽..response");
	    response.setContentType("text/html;charset=utf-8");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
