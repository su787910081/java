package com.jt.es;

import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EsTest {

    private TransportClient client;

    @Before
    public void init() throws IOException {
        // java TransportClient client 连接es
        // Settings.EMPTY 表示连接过程使用默认配置
        // 例如集群名称默认是elasticsearch
        client = new PreBuiltTransportClient(Settings.EMPTY);
        client.addTransportAddress(new InetSocketTransportAddress(
                InetAddress.getByName("192.168.142.128"), 9300));
    }

    /*
     * 客户端连接es , 执行一个方法判断连接是否成功
     */
    @Test
    public void test01() throws IOException {
        // 利用client 的方法，获取索引jtdb_item,tb_item 下id 为 830972 的doc
        GetResponse response = client
                .prepareGet("jtdb_item", "tb_item", "830972").get();
        
        // 从response 获取doc
        String result = response.getSourceAsString();
        System.out.println(result);
    }
    
    @After
    public void destroy() {
        client.close();
    }
}
