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
 * 根据商品ID 查询所要修改的商品信息
 * @author suyh
 *
 */
public class ProdInfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取商品ID
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product prod = findProdById(pid);
        request.setAttribute("prod", prod);
        request.getRequestDispatcher("/backend/prod_upd.jsp").forward(request, response);
    }

    private Product findProdById(int pid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from product WHERE id = " + pid;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product prod = new Product();
                prod.setId(rs.getInt("id"));
                prod.setName(rs.getString("name"));
                prod.setCategory(rs.getString("category"));
                prod.setPrice(rs.getDouble("price"));
                prod.setPnum(rs.getInt("pnum"));
                prod.setDescription(rs.getString("description"));
                
                return prod;
            }
        } catch (Exception e) {
            System.out.println("数据库操作失败！");
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
        
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
