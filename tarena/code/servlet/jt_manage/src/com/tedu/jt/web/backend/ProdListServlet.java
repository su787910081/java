package com.tedu.jt.web.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.jt.utils.JDBCUtils;

public class ProdListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. 查询所有商品信息
        List<Product> lsProd = findProdList();
        System.out.println("list: " + lsProd);

        // 2. 将List 存入request 域对象中
        request.setAttribute("list", lsProd);

        // 3. 通过转发将List 集合带到商品列表页面进行展示
        request.getRequestDispatcher("/backend/prod_list.jsp").forward(request, response);
    }

    /**
     * 用于查询所有的商品信息
     * 
     * @return 集合
     */
    private List<Product> findProdList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = JDBCUtils.getConn();
            String sql = "select * from product";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            List<Product> lsProd = new ArrayList<Product>();
            while (rs.next()) {
                Product prod = new Product();
                prod.setId(rs.getInt("id"));
                prod.setName(rs.getString("name"));
                prod.setCategory(rs.getString("category"));
                prod.setPrice(rs.getDouble("price"));
                prod.setPnum(rs.getInt("pnum"));
                prod.setDescription(rs.getString("description"));
                
                lsProd.add(prod);
            }
            
            return lsProd;
        } catch (Exception e) {
            System.out.println("商品信息查询失败");
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
