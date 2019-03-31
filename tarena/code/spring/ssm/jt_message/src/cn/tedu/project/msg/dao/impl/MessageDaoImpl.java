package cn.tedu.project.msg.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.tedu.project.msg.dao.MessageDao;
import cn.tedu.project.msg.entity.Message;

public class MessageDaoImpl implements MessageDao {

	/**
	 * @return 表示写入到数据库中数据的行数
	 */
	@Override
	public int insertObject(Message msg) {
		// 1. 创建连接
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rows = -1;
		String sql = "insert into msg(content) values(?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///test", "root", "suyunfei");
			// 2. 创建statment
			pstmt = conn.prepareStatement(sql);
			// 3. 发送SQL 
			pstmt.setString(1, msg.getContent());
			rows = pstmt.executeUpdate();
			// 4. 处理结果
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 5. 释放资源
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
				
		return rows;
	}
	
	public static void main(String[] args) {
		MessageDao dao = new MessageDaoImpl();
		Message msg = new Message();
		msg.setContent("MVC Content");
		dao.insertObject(msg);
		
		System.out.println("MessageDaoImpl.main()");
	}
}
