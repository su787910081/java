package com.tedu.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 负责处理支付请求
 * @author suyh
 *
 */
public class PayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. 获取Session 对象
        HttpSession session = request.getSession();
        
        // 2. 通过Session 获取需要支付的商品
        String prod = (String)session.getAttribute("prod");
        
        // 3. 对商品进行结算
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("恭喜您成功为" + prod + "商品支付100$...");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
