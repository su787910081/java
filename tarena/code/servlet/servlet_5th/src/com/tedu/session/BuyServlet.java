package com.tedu.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 接收浏览器的请求，将商品加入购物车(Session)中。
 * 
 * @author suyh
 *
 */
public class BuyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. 获取商品信息
        String prod = request.getParameter("prod");
        // prod = new String(prod.getBytes("iso8859-1"), "utf-8")); // GET
        // 中文乱码问题

        // 2. 获取Session 对象
        HttpSession session = request.getSession();
        // >> 设置Cookie 的最大生存时间
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath(request.getContextPath() + "/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        // 3. 将商品加入购物车(session 对象)
        session.setAttribute("prod", prod);

        // 4. 提示用户
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("您成功将" + prod + " 加到购物车");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
