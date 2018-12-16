package com.tedu.request.include;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 将 AServlet 包含进来，和当前Servlet 一起来处理请求
	    request.getRequestDispatcher("/AServlet").include(request, response);
	    
	    response.getWriter().write("BServlet...<br />");
	    
	    // 将 CServlet 包含进来，和当前Servlet 一起来处理请求
	    request.getRequestDispatcher("/CServlet").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
