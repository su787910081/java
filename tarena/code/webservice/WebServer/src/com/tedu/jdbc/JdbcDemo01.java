package com.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JdbcDemo01 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;

		try {
			// 1. 注册数据库驱动
			// 在真实开发中我们一般不会通过这种方式注册驱动，
			// 因为这种方式的两个问题：1. 可能会导致驱动注册两次；2. 会导致程序和具体的数据库驱动绑死在一起
			// 解决方案一：通过反射机制，Class.forName("com.mysql.jdbc.Driver");
			// 这种方式驱动只会注册一次，因为在源码中可以看到类Driver 中有一个静态代码块，这个代码块就是注册驱动。
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 获取数据库连接 java.sql.Connection 而非 com.mysql.jdbc.Connection
			// 数据库的URL 用来指定访问哪一个位置上的数据库服务器以及数据库中哪一个数据库
			// 协议名：jdbc:mysql
			// 主机名端口: localhost:3306
			// 数据库名: jt_db
			// 简写形式：jdbc:mysql:///jt_db
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jt_db", "suyh", "suyunfei");

			// 3. 获取传输器 java.sql.Statement 而非com.mysql.jdbc.Statement
			state = conn.createStatement();

			// 4. 利用传输器发送SQL 到数据库执行
			String sql = "select * from account";
			rs = state.executeQuery(sql);

			// 5. 打印结果
			while (rs.next()) {
				// 获取一行数据
				// 按列获取数据时，下标从1 开始
				// int id = rs.getInt(1);
				// String name = rs.getString(2);
				// int age = rs.getInt(3);
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double money = rs.getDouble("money");

				System.out.println("id: " + id + ", name: " + name + ", age: " + money);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (state != null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					state = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
	}
}
