package com.tedu.dbpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tedu.jdbc.JdbcUtils;

public class TestMyPool {
    public static void main(String[] args) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;

        MyPool pool = new MyPool();

        try {
            // 从连接池中获取连接
            conn = pool.getConnection();
            state = conn.createStatement();
            String sql = "select * from account where id = 3";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name") + ": " + rs.getDouble("money"));
            }

        } catch (Exception e) {

        } finally {
            // 千万记住！！！ 连接用完不要关闭，而是要还回连接池
//            pool.returnConn(conn);
            // 连接不能关，但其他的需要关闭
            JdbcUtils.close(conn, state, rs);
        }
    }
}
