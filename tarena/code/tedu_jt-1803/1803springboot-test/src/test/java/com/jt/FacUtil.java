package com.jt;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FacUtil {
    public static Connection getConn() throws Exception { 
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.2");
        factory.setPort(5672);
        factory.setUsername("jtadmin");
        factory.setPassword("123456");
        factory.setVirtualHost("/jt");
        Connection conn = factory.newConnection();
        return conn;
    }
}
