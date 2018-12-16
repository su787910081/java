package com.tedu.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

/**
 * request 对象获取请求参数
 * 
 * @author suyh
 *
 */
public class RequestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		demo02(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void demo01(HttpServletRequest request, HttpServletResponse response) {
		// 1. getParameter()
		String strUsername = request.getParameter("username");
		System.out.println("username: " + strUsername);

		String like = request.getParameter("like");
		System.out.println("like: " + like);

		// 2. getParameterValues
		String[] likes = request.getParameterValues("like");
		System.out.println("likes: " + Arrays.toString(likes));

		// 3. getParameterMap()
		Map<String, String[]> m = request.getParameterMap();
		System.out.println("map: " + m);

		// 4. getParameterNames();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			System.out.println(name + " ==> " + value);
		}
	}
	
	private void demo02(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		System.out.println("username = " + username);
		
		if (username == null) {
			return;
		}
		
		// 将乱码逆向编码转回二进制数组
//		byte[] bysUsername;
//		try {
//			bysUsername = username.getBytes("iso8859-1");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return;
//		}
//		System.out.println(bysUsername);
//		// 手动编码，将二进制数组转成字符串(查询utf-8 这个码表)
//		String strUsername = new String(bysUsername, "utf-8");
//		System.out.println("username = " + strUsername);
	}

}
