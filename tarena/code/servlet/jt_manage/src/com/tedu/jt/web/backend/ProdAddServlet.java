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

/**
 * 处理商品添加请求
 * 
 * @author suyh
 *
 */
public class ProdAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 0. 处理乱码
        // >> 请求参数乱码(POST)
        request.setCharacterEncoding("UTF-8");
        
        response.setContentType("text/html; charset=utf-8");

        // 1. 获取商品信息
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        String description = request.getParameter("description");

        // 2. 将商品信息保存到数据库中
        addProd(name, category, price, pnum, description);

        // 3. 提示用户商品添加成功
        response.getWriter().write("<h1 style='color:red;text-align:center;'>");
        response.getWriter().write("商品添加成功，3秒之后将会跳转到商品列表页面");
        response.getWriter().write("</h1>");

        // 4. 定时刷新到商品列表页面
        response.setHeader("Refresh",
                "3;url=" + request.getContextPath() + "/backend/prod_list.jsp");
    }

    /**
     * 将商品信息保存到数据库中
     * 
     * @param name
     * @param category
     * @param price
     * @param pnum
     * @param description
     */
    private void addProd(String name, String category, double price, int pnum, String description) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            // 1. 获取连接
            conn = JDBCUtils.getConn();
            
            // 2. 创建 SQL 语句
            String sql = "insert into product values(null, ?,?,?,?,?)";
            
            // 3. 获取传输器
            ps = conn.prepareStatement(sql);
            
            // 4. 设置SQL 参数
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, pnum);
            ps.setString(5, description);
            
            // 5. 执行SQL
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("商品添加失败！！");
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
