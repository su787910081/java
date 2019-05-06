package com.jt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@SpringBootApplication
@RestController
public class JedisStarter {
    public static void main(String[] args) {
        SpringApplication.run(JedisStarter.class, args);
    }
    
    @Autowired
    private ShardedJedisPool pool;
    
    @RequestMapping("getData")
    public String getValue(String id) {
        // 根据ID 生成key 值
        String key = "ITEM_" + id;
        
        ShardedJedis sJedis = pool.getResource();
        String value = sJedis.get(key);
        
        return value;
    }
    
    @RequestMapping("setData")
    public String setValue(String id, String value) {
        pool.getResource().set("ITEM_" + id, value);
        
        return "添加成功, OK";
    }
    
    @Autowired
    private JedisCluster cluster;
    
    @RequestMapping("cluster")
    public String clusterSetGet(String key, String value) {
        System.out.println("key: " + key + ", value: " + value);
        cluster.set(key, value);

        return cluster.get(key);
    }
    
    
    
}
