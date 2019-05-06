package com.jt;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class ClusterTest {

    @Test
    public void clusterConnection() throws IOException {
        // 收集节点信息，整个集群，任意提供至少1 个节点数量信息即可
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.142.128", 8000));
        nodes.add(new HostAndPort("192.168.142.128", 8001));
//        nodes.add(new HostAndPort("192.168.142.128", 8002));
        
        // 构造对象之前，需要先创建一个配置对象
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(200);
        
        // jedis 操作分布式集群，使用的分片对象。操作redis-cluster 不需要分片计算
        // jedisCluster 对象; 构造原理在整合之后讨论
        JedisCluster cluster = new JedisCluster(nodes, 1000, config);
        for (int i = 0; i < 100; i++) {
            String key = "jt_key_" + i;
            String value = "value_" + i;
            
            cluster.set(key, value);
        }
        
        for (int i = 0; i < 100; i++) {
            System.out.println(cluster.get("jt_key_" + i));
        }
        
        cluster.close();
    }
}
