package com.tedu.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcCrud {
	public static void main(String[] args) {
	    query();
		
	}
	
	public static void add() {
		
	}
	
	public static void query() {
	    Connection conn = null;
	    Statement state = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = JdbcUtils.getConnection();
	        state = conn.createStatement();
	        String sql = "select * from account where id = 1";
	        rs = state.executeQuery(sql);
	        
	        if (rs.next()) {
	            System.out.println(rs.getInt("id") + ":" + rs.getString("name") + ":" + rs.getDouble("money"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
            JdbcUtils.close(conn, state, rs);
        }
	   
	}
}
