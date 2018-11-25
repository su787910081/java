package com.tedu.dbpool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.tedu.decorator.ConnDecorator;
import com.tedu.jdbc.JdbcUtils;

// 1. 写一个类MyPool, 实现DataSource 接口
public class MyPool implements DataSource {
    // 2. 创建一个容器(LinkedList), 当做连接池，用于存放数据库连接
    private static List<Connection> list = new LinkedList<>();

    // 3. 提供一个静态块，在程序启动时，初始化一批连接放在连接池中共享
    static {
        for (int i = 0; i < 5; ++i) {
            Connection conn = JdbcUtils.getConnection();
            list.add(conn);
        }
    }

    // 4. 实现getConnection 方法，用来获取连接
    @Override
    public Connection getConnection() throws SQLException {
        if (list.size() <= 0) {
            for (int i = 0; i < 5; ++i) {
                Connection conn = JdbcUtils.getConnection();
                list.add(conn);
            }
        }
        
        Connection conn = list.remove(0);
        
        Connection connDecorate = new ConnDecorator(conn, this);
        System.out.println("成功获取一个连接，池中还剩余连接个数：" + list.size());
        return connDecorate;
    }

    // 5. 提供一个returnConn 方法，用来将连接还回连接池中
    public void returnConn(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                list.add(conn);
                System.out.println("成功还回一个连接，池中还剩余连接个数：" + list.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
