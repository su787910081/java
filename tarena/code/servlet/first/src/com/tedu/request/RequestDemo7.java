package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.getWriter().write("demo...7");
	    
	    // 通过request 对象上的map 集合，获取属性
	    String strName = (String)request.getAttribute("name");
	    String strNickname = (String)request.getAttribute("nickname");
	    String strAge = (String)request.getAttribute("age");
	    
	    System.out.println("name: " + strName + ", nickname: " + strNickname + ", age: " + strAge);
	    
	    request.getRequestDispatcher("/demo1.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
