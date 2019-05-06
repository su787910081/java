package com.jt.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
public class RedisConfig {

    // 利用属性读取配置文件数据
    @Value("${spring.redis.nodes}")
    private String nodes;
    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;
    @Value("${spring.redis.pool.max-total}")
    private Integer maxTotal;
    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWait;
    
    public JedisPoolConfig getConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);
        
        return config;
    }
    
    @Bean
    public ShardedJedisPool getPool() {
        String[] listNode = nodes.split(", ");
        
        List<JedisShardInfo> listInfo = new ArrayList<JedisShardInfo>();
        
        for (String node : listNode) {
            if ("".equals(node)) {
                System.out.println("RedisConfig.getPool(), empty node");
                continue;
            }
            
            String[] host = node.split(":");
            if (host.length != 2) {
                throw new RuntimeException();
            }
            
            JedisShardInfo info = new JedisShardInfo(host[0], host[1]);
            listInfo.add(info);
        }
        
        ShardedJedisPool pool = new ShardedJedisPool(getConfig(), listInfo);
        return pool;
    }
}
