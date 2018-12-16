package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. getRequestURL();
		StringBuffer url = request.getRequestURL();
		String strUrl = url.toString();
		System.out.println("url: " + strUrl);	// http://localhost/first/RequestDemo1
		System.out.println("uri: " + request.getRequestURI());	// /first/RequestDemo1
		System.out.println("RequestDemo1.doGet()");	// syst   "alt+/  快捷键"
		
		// 2. getMethod 获取请求方式
		String method = request.getMethod();
		System.out.println("method: " + method);
		
		// 3. getContextPath 获取WEB 应用对外访问的虚拟路径
		String strContextPath = request.getContextPath();
		System.out.println("context path: " + strContextPath);	//  /first
		
		// 请求重定向
		// response.sendRedirect(strContextPath + "/index.html");
		
		// 4. getHeader() -- 获取请求头
		String strHostHeader = request.getHeader("Host");
		System.out.println("host: " + strHostHeader);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
