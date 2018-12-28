package com.tedu.jt.web.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.jt.utils.JDBCUtils;

public class ProdDelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("pid"));
        delProduct(pid);
        
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<h1>");
        response.getWriter().write("商品删除成功，2秒后将会跳转到商品列表页面!!");
        response.getWriter().write("</h1>");
        
        response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/ProdListServlet");
    }

    private void delProduct(int pid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConn();
            String sql = "delete from product where id = " + pid;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("商品删除失败");
            e.printStackTrace();
            throw new RuntimeException("商品删除失败");
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
