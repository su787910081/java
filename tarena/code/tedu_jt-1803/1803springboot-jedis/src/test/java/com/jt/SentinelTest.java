package com.jt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class SentinelTest {
    @Test
    public void sentinel(){
        //多个哨兵之间没有监控关系，顺序遍历，哪个能通就走哪个，不通，再检查下一个哨兵
        Set<String> sentinels = new HashSet<String>();
        sentinels.add(new HostAndPort("10.42.170.247",26382).toString());
        sentinels.add(new HostAndPort("10.42.170.247",26383).toString());
        sentinels.add(new HostAndPort("10.42.170.247",26383).toString());
        
        //mymaster是在sentinel.conf中配置的名称
        //sentinel monitor mymaster 192.168.163.200 6380 1
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
        System.out.println("当前master：" + pool.getCurrentHostMaster());

        Jedis jedis = pool.getResource();
        //jedis.auth("123456");

        System.out.println(jedis.get("num"));
        pool.returnResource(jedis);   

        pool.destroy();
        System.out.println("ok");
    }
}
