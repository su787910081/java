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

import sun.util.locale.ParseStatus;

/**
 * 修改商品信息
 * @author suyh
 *
 */
public class ProdUpdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 1. 获取商品信息
        int pid = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        String description = request.getParameter("description");
        
        updateProdInfo(pid, name, category, price, pnum, description);

        response.getWriter().write("<h1 style='color:red;text-align:center'>");
        response.getWriter().write("商品信息修改成功，2秒之后将会跳转到商品列表页面..");
        response.getWriter().write("</h1>");
        
        response.setHeader("Refresh", "3;url=" + request.getContextPath() + "/ProdListServlet");
    }

    private void updateProdInfo(int pid, String name, String category, double price, int pnum, String description) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = JDBCUtils.getConn();
            String sql = "update product set name=?, category=?, price=?, pnum=?, description=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, pnum);
            ps.setString(5, description);
            ps.setInt(6, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("更新商品信息失败");
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
