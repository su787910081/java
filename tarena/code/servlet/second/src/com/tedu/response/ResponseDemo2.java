package com.tedu.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// response 实现请求重定向
// response 实现定时刷新
public class ResponseDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("ResponseDemo2.doGet()");
	    
	    // location(request, response);
	    refresh(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void refresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /*
         * response 实现定时刷新
         */
	    response.setContentType("text/html;charset=utf-8");
	    response.getWriter().write("3秒之后跳转到首页.");
	    // 3 秒之后跳转
	    response.setHeader("refresh", "3;url=" + request.getContextPath() + "/index.html");
	}
	protected void location(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /*
         * response 实现请求重定向
         */
        // 设置302 状态码
        // response.setStatus(302);
        // 设置location 响应头
        // response.setHeader("location", request.getContextPath() + "/index.html");
        
        response.sendRedirect(request.getContextPath() + "/index.html");
           
	}
}
