package com.tedu.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 请求转发
	    // request.getRequestDispatcher("/index.html").forward(request, response);
	    
	    // 2. 请求重定向
	    // response.sendRedirect(request.getContextPath() + "/index.html");
	    // response.sendRedirect("/first/index.jsp");
	    response.sendRedirect("http://www.baidu.com");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
