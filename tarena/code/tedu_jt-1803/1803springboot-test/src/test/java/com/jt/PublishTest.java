package com.jt;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;


public class PublishTest {
    // 生产者
    @Test
    public void pro() throws Exception {
        Connection conn = FacUtil.getConn();
        
        Channel chan = conn.createChannel();
        // 参数: exchange 交换机名称, 
        // type 交换机类型名称
        // 发布订阅
        chan.exchangeDeclare("1803pEx", "fanout");
        for (int i = 0; i < 1000; i++) {
            String msg = "hello rm msg, number: " + i;
            chan.basicPublish("1803pEx", "", null, msg.getBytes());
        }
        
        chan.close();
        conn.close();
    }
    
    // 消费者
    @Test
    public void con() throws Exception {
        Connection conn = FacUtil.getConn();
        Channel chan = conn.createChannel();
        
        chan.exchangeDeclare("1803pEx", "fanout");
        
        chan.queueDeclare("work", false, false, false, null);
        chan.queueBind("work", "1803pEx", "");
        
        QueueingConsumer consumer = new QueueingConsumer(chan);
        
        chan.basicConsume("work", true, consumer);
        
        while (true) {
            Delivery deli = consumer.nextDelivery();
            String body = new String(deli.getBody());
            System.out.println(body);
        }
    }
    
}
