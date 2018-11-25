package com.tedu.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tedu.jdbc.JdbcUtils;

// 通过PreparedStatement 对象来实现批处理
public class PsBatch {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            long begin = System.currentTimeMillis();
            
            conn = JdbcUtils.getConnection();
            System.out.println("是否自动提交：" + conn.getAutoCommit());
            // 将自动提交事务关闭
            conn.setAutoCommit(false);
            
            String sql = "insert into batch values(null, ?)";
            ps = conn.prepareStatement(sql);
            // 设置参数(使用批处理)
            for (int i = 0; i < 100; ++i) {
                ps.setString(1, "zhangsz" + (i + 1));
                ps.addBatch();  // 将SQL 添加到批处理中
            }
            
            ps.executeBatch();
            
            // 当所有SQL 执行完成后，作一次提交
            conn.commit();
            
            System.out.println("执行完成");
            
            long end = System.currentTimeMillis();
            System.out.println("所用时间: " + (end - begin));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, ps, rs);
        }

    }
}
