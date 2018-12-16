package com.tedu.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request 实现请求转发
 * @author suyh
 *
 */
public class RequestDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RequestDemo4.doGet()");
		
		response.getWriter().write("demo4...");
		// 如果在调用forward 之前，将write 的数据强制刷新，则forward 将会失败
		// response.flushBuffer(); -- 强制刷新则会导致下面的forward 失败。

		// 将请求转发到RequestDemo5 来处理 // 转发前会先将response 里面的数据先清空。即"demo4..." 将不会保存在response 的缓冲区中
		request.getRequestDispatcher("/RequestDemo5").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
