package com.jt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {
    @Autowired
    private ShardedJedisPool pool;
    
    public void set(String key, String value) {
        ShardedJedis sJedis = pool.getResource();
        
        sJedis.set(key, value);
        
        pool.returnResource(sJedis);
    }
}
