package com.jt.configuration;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class ClusterConfiguration {

    // 利用属性读取配置文件数据
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;
    @Value("${spring.redis.pool.max-total}")
    private Integer maxTotal;
    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWait;

    // 传递给cluster 的配置对象
    public GenericObjectPoolConfig getConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);

        return config;
    }

    @Bean
    public JedisCluster getCluster() {
        String[] listNode = nodes.split(", ");

        Set<HostAndPort> infoSet = new HashSet<HostAndPort>();

        for (String node : listNode) {
            if ("".equals(node)) {
                System.out.println("RedisConfig.getPool(), empty node");
                continue;
            }

            String[] host = node.split(":");
            if (host.length != 2) {
                throw new RuntimeException();
            }

            HostAndPort info = new HostAndPort(host[0],
                    Integer.parseInt(host[1]));
            infoSet.add(info);
        }

        JedisCluster cluster = new JedisCluster(infoSet, getConfig());
        return cluster;
    }
}
