package com.tedu.cookie;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String strCurDate = new Date().toString();
	    
	    Cookie[] cookies = request.getCookies();
	    String strPreDate = "没有";
	    if (cookies != null) {
	        for (Cookie c : cookies) {
	            if (c.getName().equals("time")) {
	                strPreDate = c.getValue();
	                break;
	            }
	        }
	    }
	    
	    Cookie cookie = new Cookie("time", strCurDate);
	    cookie.setMaxAge(3600);    // 1小时 = 3600
	    cookie.setPath(request.getContextPath() + "/");    // 设置Cookie 被带回来的路径
	    response.setContentType("text/html;charset=utf-8");
	    
	    response.addCookie(cookie);    // ***** 如果cookie 中有中文数据，那么他的编解码是怎么处理的呢？
	    response.getWriter().write("您上次登录的时间: " + strPreDate);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
