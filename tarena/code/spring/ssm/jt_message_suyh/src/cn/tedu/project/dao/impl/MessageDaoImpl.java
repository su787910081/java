package cn.tedu.project.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.jdbc.Driver;

import cn.tedu.project.dao.MessageDao;
import cn.tedu.project.entity.Message;

public class MessageDaoImpl implements MessageDao {

	@Override
	public int insertMsg(Message msg) {
		// 1. 连接数据库
		Connection conn = null;
		PreparedStatement pstat = null;
		int rows = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///test", "root", "suyunfei");
			pstat = conn.prepareStatement("insert into msg(content) values(?)");
			pstat.setString(1, msg.getContent());
			rows = pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (pstat != null) {
				try { pstat.close(); } catch (Exception e) { }
			}
			if (conn != null) {
				try { conn.close(); } catch (Exception e) { }
			}
			pstat = null;
			conn = null;
		}
		
		return rows;
	}
	
	public static void main(String[] args) {
		MessageDao md = new MessageDaoImpl();
		
		Message msg = new Message();
		msg.setContent("suyh test first");
		md.insertMsg(msg);
		
		System.out.println("MessageDaoImpl.main()");
	}

}
