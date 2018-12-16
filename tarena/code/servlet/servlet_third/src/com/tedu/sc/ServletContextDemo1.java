package com.tedu.sc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 统计网站访问数量
public class ServletContextDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 1. 获取表示本网站访问人数的属性
	    Object count = this.getServletContext().getAttribute("count");
	    int nCount = 0;
	    if (count != null) {
	        nCount = (Integer)count;
	    }
	    this.getServletContext().setAttribute("count", nCount + 1);
	    
	    response.setContentType("text/html;charset=utf-8");
	    response.getWriter().write("您是本网站第" + (nCount + 1) + "位访客");
	    // ServletContext
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
