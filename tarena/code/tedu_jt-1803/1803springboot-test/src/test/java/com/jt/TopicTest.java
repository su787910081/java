package com.jt;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class TopicTest {
    // 生产者
    @Test
    public void pro() throws Exception {
        Connection conn = FacUtil.getConn();

        Channel chan = conn.createChannel();
        // 参数: exchange 交换机名称,
        // type 交换机类型名称
        // 发布订阅
        chan.exchangeDeclare("1803topicEx", "topic");
        for (int i = 0; i < 1000; i++) {
            String msg = "正在添加商品, number: " + i;
            chan.basicPublish("1803topicEx", "jt.item.add", null, msg.getBytes());
            System.out.println("发送了, number: " + i);
        }

        chan.close();
        conn.close();
    }

    // 完成两个监听消费者
    @Test
    public void con1() throws Exception {
        Connection conn = FacUtil.getConn();

        Channel chan = conn.createChannel();
        // 声明交换机，声明队列
        chan.exchangeDeclare("1803topicEx", "topic");
        chan.queueDeclare("routing", false, false, false, null);
        // 绑定关系
        chan.queueBind("routing", "1803topicEx", "jt.#");
        chan.basicQos(1);
        // 消费者
        QueueingConsumer consumer = new QueueingConsumer(chan);
        // 绑定consumer
        chan.basicConsume("routing", true, consumer);
        while (true) {
            Delivery deli = consumer.nextDelivery();
            System.out.println("路由消费者1: " + new String(deli.getBody()));
        }
    }
    
    @Test
    public void con2() throws Exception {
        Connection conn = FacUtil.getConn();

        Channel chan = conn.createChannel();
        // 声明交换机，声明队列
        chan.exchangeDeclare("1803topicEx", "topic");
        chan.queueDeclare("routing4", false, false, false, null);
        // 绑定关系
        chan.queueBind("routing4", "1803topicEx", "jt.*.*");
        chan.basicQos(1);
        // 消费者
        QueueingConsumer consumer = new QueueingConsumer(chan);
        // 绑定consumer
        chan.basicConsume("routing4", true, consumer);
        while (true) {
            Delivery deli = consumer.nextDelivery();
            System.out.println("路由消费者2: " + new String(deli.getBody()));
        }
    }
}
