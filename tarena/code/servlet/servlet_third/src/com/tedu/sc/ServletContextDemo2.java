package com.tedu.sc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Object count = this.getServletContext().getAttribute("count");
        int nCount = 0;
        if (count != null) {
            nCount = (Integer)count;
        }
        this.getServletContext().setAttribute("count", nCount + 1);
        
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("您是本网站第" + (nCount + 1) + "位访客");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
