package com.jt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestJedis {

    /**
     * 使用jedis 创建客户端 连接redis服务，测试方法完成功能调用
     */
    @Test
    public void jedisConnection() {
        Jedis jedis = new Jedis("192.168.142.128", 6379);
        jedis.set("country", "china");
        for (int i = 0; i < 10; i++) {
            // ""
            // jedis.set(key, value);
        }
        jedis.close();
    }

    @Test
    public void cacheLogin() {
        System.out.println("用户请求访问端口: 1306272");
        // 到缓存获取数据/判断数据, 根据端口ID生成自定义KEY
        String key = "ITEM_1306272";
        // 执行缓存逻辑判断商品
        Jedis jedis = new Jedis("192.168.142.128", 6379);
        if (jedis.exists(key)) {
            String result = jedis.get(key);
            System.out.println("从缓存获取商品信息: " + result);
        } else {
            String item = "{'id': 1306272, 'title':'华夏大平板'}";
            System.out.println("从数据库获取端口信息: " + item);
            jedis.set(key, item);
        }

        jedis.close();
    }

    @Test
    public void autoShard() {
        String haha = new String("dsjflkje");
        System.out.println(haha.hashCode());

        Jedis jedis6379 = new Jedis("192.168.142.128", 6379);
        Jedis jedis6380 = new Jedis("192.168.142.128", 6380);
        Jedis jedis6381 = new Jedis("192.168.142.128", 6381);

        for (int i = 0; i < 100; i++) {
            Integer key = i;
            String value = "value" + i;

            if (key <= 30) {
                jedis6379.set(key + "", value);
            } else if (key <= 60) {
                jedis6380.set(key + "", value);
            } else {
                jedis6381.set(key + "", value);
            }
        }

        Integer redKey = 61;
        String value = null;
        if (redKey <= 30) {
            value = jedis6379.get(redKey + "");
        } else if (redKey <= 60) {
            value = jedis6380.get(redKey + "");
        } else {
            value = jedis6381.get(redKey + "");
        }
        System.out.println("value: " + value);

        jedis6379.close();
        jedis6380.close();
        jedis6381.close();
    }

    @Test
    public void hashShard() {
        Jedis jedis6379 = new Jedis("192.168.142.128", 6379);
        Jedis jedis6380 = new Jedis("192.168.142.128", 6380);
        Jedis jedis6381 = new Jedis("192.168.142.128", 6381);

        for (int i = 0; i < 100; i++) {
            String key = "item_1803_" + i;
            String value = "item_1803_value_" + i;

            int num = (key.hashCode() & Integer.MAX_VALUE) % 3;

            if (num == 0) {
                jedis6379.set(key + "", value);
            } else if (num == 1) {
                jedis6380.set(key + "", value);
            } else {
                jedis6381.set(key + "", value);
            }
        }

        String key = "item_1803_" + 61;

        int num = (key.hashCode() & Integer.MAX_VALUE) % 3;

        String value;
        if (num == 0) {
            value = jedis6379.get(key);
        } else if (num == 1) {
            value = jedis6380.get(key);
        } else {
            value = jedis6381.get(key);
        }

        System.out.println(value);

        jedis6379.close();
        jedis6380.close();
        jedis6381.close();
    }

    @Test
    public void jedisHashShard() {
        // 分片对象底层实现了分片的逻辑，只需要收集所有的连接信息，
        // 自动完成 分片计算，调用方法和jedis 一模一样
        // 收集redis 节点信息
        List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();

        JedisShardInfo info1 = new JedisShardInfo("192.168.142.128", 6379);
        JedisShardInfo info2 = new JedisShardInfo("192.168.142.128", 6380);
        JedisShardInfo info3 = new JedisShardInfo("192.168.142.128", 6381);

        infoList.add(info1);
        infoList.add(info2);
        infoList.add(info3);

        // 利用list 对象，构造一个分片连接对象
        ShardedJedis sJedis = new ShardedJedis(infoList);
        sJedis.set("hahahaha", "jt is good very good");
        System.out.println(sJedis.get("hahahaha"));
    }

    /**
     * jedis 分片连接池
     */
    @Test
    public void jedisPool() {
        // 利用节点信息，配置对象：最大连接数，最小连接数，最大空闲数
        // 连接超时，connctTime, socketTime
        // 收集信息

        // 分片对象底层实现了分片的逻辑，只需要收集所有的连接信息，
        // 自动完成 分片计算，调用方法和jedis 一模一样
        // 收集redis 节点信息
        List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();

        JedisShardInfo info1 = new JedisShardInfo("192.168.142.128", 6379);
        JedisShardInfo info2 = new JedisShardInfo("192.168.142.128", 6380);
        JedisShardInfo info3 = new JedisShardInfo("192.168.142.128", 6381);

        infoList.add(info1);
        infoList.add(info2);
        infoList.add(info3);
        
        // 构造一个具有配置条件的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8); // 最大空闲连接数量
        config.setMaxTotal(200);    // 最大连接数量
        
        // 利用配置对象和连接信息构造连接池对象
        ShardedJedisPool pool = new ShardedJedisPool(config, infoList);
        
        // 从pool 获取分片对象操作集群
        ShardedJedis sJedis = pool.getResource();
        pool.returnResource(sJedis);
    }
    
    @Test
    public void testStrings() {
        String nodes = "192.168.142.128:6379,192.168.142.128:6380,192.168.142.128:6381";
        String[] listNode = nodes.split(",");
        System.out.println(Arrays.toString(listNode));
        for (String node : listNode) {
            String[] n = node.split(":");
            System.out.println(Arrays.toString(n));
        }
    }
}
