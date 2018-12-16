package com.tedu.cookie;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDemo1
 */
public class CookieDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 获取本次访问时间
	    String strCurDate = new Date().toString();
	    
	    // 通过Set-Cookie 响应头将时间发送给浏览器保存
	    response.setHeader("Set-Cookie", "time = " + strCurDate);
	    
	    // 通过Cookie 请求头获取上次访问时间
	    String strPreDate = request.getHeader("Cookie");
	    // 将时间作为响应数据发送给浏览器，并提示用户上次访问的时间
	    response.setContentType("text/html;charset=utf-8");
	    response.getWriter().write("您上次访问的时间: " + strPreDate);
	    
	    request.getRemoteAddr();   // 获取浏览器的IP 地址
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
