package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // request 对象上提供了一个map 集合
	    request.setAttribute("name", "刘德华");
	    request.setAttribute("nickname", "andy");
	    request.setAttribute("age", "18");
	    
	    // 将请求转发给RequestDemo7
	    request.getRequestDispatcher("/RequestDemo7").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
