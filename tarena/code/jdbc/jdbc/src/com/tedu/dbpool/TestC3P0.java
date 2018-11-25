package com.tedu.dbpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tedu.jdbc.JdbcUtils;

public class TestC3P0 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;

        ComboPooledDataSource pool = new ComboPooledDataSource();

        try {
            // 设置数据库连接的基本信息
//            pool.setDriverClass("com.mysql.jdbc.Driver");
//            pool.setJdbcUrl("jdbc:mysql://localhost:3306/jt_db");
//            pool.setUser("suyh");
//            pool.setPassword("suyunfei");
            
            // 从连接池中获取连接
            conn = pool.getConnection();
            state = conn.createStatement();
            String sql = "select * from account where id = 3";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name") + ": " + rs.getDouble("money"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, state, rs);
        }
    }
}
