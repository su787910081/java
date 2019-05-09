


- > ## 连接到redis 服务器

    > pom 依赖
    >> <details>
    >> <summary><mark><font color=darkred>redis 组件依赖</font></mark></summary>
    >> 
	>>      <dependency>
	>>          <groupId>org.springframework.boot</groupId>
	>>          <artifactId>spring-boot-starter-redis</artifactId>
	>>          <version>1.4.5.RELEASE</version>
	>>      </dependency>
    >> </details>
    > demo
    >> <details>
    >> <summary><mark><font color=darkred>缓存demo</font></mark></summary>
    >> 
    >>      @Test
    >>      public void cacheLogin() {
    >>          System.out.println("用户请求访问端口: 1306272");
    >>          // 到缓存获取数据/判断数据, 根据端口ID生成自定义KEY
    >>          String key = "ITEM_1306272";
    >>          // 执行缓存逻辑判断商品
    >>          Jedis jedis = new Jedis("192.168.142.128", 6379);
    >>          if (jedis.exists(key)) {
    >>              String result = jedis.get(key);
    >>              System.out.println("从缓存获取商品信息: " + result);
    >>          } else {
    >>              String item = "{'id': 1306272, 'title':'华夏大平板'}";
    >>              System.out.println("从数据库获取端口信息: " + item);
    >>              jedis.set(key, item);
    >>          }
    >>      
    >>          jedis.close();
    >>      }
    >> </details>
    >> <details>
    >> <summary><mark><font color=darkred>分片连接池-demo</font></mark></summary>
    >> 
    >>      @Test
    >>      public void jedisPool() {
    >>          // 利用节点信息，配置对象：最大连接数，最小连接数，最大空闲数
    >>          // 连接超时，connctTime, socketTime
    >>          // 收集信息
    >>      
    >>          // 分片对象底层实现了分片的逻辑，只需要收集所有的连接信息，
    >>          // 自动完成 分片计算，调用方法和jedis 一模一样
    >>          // 收集redis 节点信息
    >>          List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
    >>      
    >>          JedisShardInfo info1 = new JedisShardInfo("192.168.142.128", 6379);
    >>          JedisShardInfo info2 = new JedisShardInfo("192.168.142.128", 6380);
    >>          JedisShardInfo info3 = new JedisShardInfo("192.168.142.128", 6381);
    >>      
    >>          infoList.add(info1);
    >>          infoList.add(info2);
    >>          infoList.add(info3);
    >>          
    >>          // 构造一个具有配置条件的配置对象
    >>          JedisPoolConfig config = new JedisPoolConfig();
    >>          config.setMaxIdle(8); // 最大空闲连接数量
    >>          config.setMaxTotal(200);    // 最大连接数量
    >>          
    >>          // 利用配置对象和连接信息构造连接池对象
    >>          ShardedJedisPool pool = new ShardedJedisPool(config, infoList);
    >>          
    >>          // 从pool 获取分片对象操作集群
    >>          ShardedJedis sJedis = pool.getResource();
    >>          pool.returnResource(sJedis);
    >>      }
    >> </details>
    > 将连接池通过springboot 交由spring bean 进行管理
    >> <details>
    >> <summary><mark><font color=darkred>class RedisConfig</font></mark></summary>
    >> 
    >>      import org.springframework.beans.factory.annotation.Value;
    >>      import org.springframework.context.annotation.Bean;
    >>      import org.springframework.context.annotation.Configuration;
    >>      
    >>      import redis.clients.jedis.JedisPoolConfig;
    >>      import redis.clients.jedis.JedisShardInfo;
    >>      import redis.clients.jedis.ShardedJedisPool;
    >>      
    >>      @Configuration
    >>      public class RedisConfig {
    >>      
    >>          // 利用属性读取配置文件数据
    >>          @Value("${spring.redis.nodes}")
    >>          private String nodes;
    >>          @Value("${spring.redis.pool.max-idle}")
    >>          private Integer maxIdle;
    >>          @Value("${spring.redis.pool.min-idle}")
    >>          private Integer minIdle;
    >>          @Value("${spring.redis.pool.max-total}")
    >>          private Integer maxTotal;
    >>          @Value("${spring.redis.pool.max-wait}")
    >>          private Integer maxWait;
    >>          
    >>          public JedisPoolConfig getConfig() {
    >>              JedisPoolConfig config = new JedisPoolConfig();
    >>              config.setMaxIdle(maxIdle);
    >>              config.setMaxTotal(maxTotal);
    >>              config.setMinIdle(minIdle);
    >>              config.setMaxWaitMillis(maxWait);
    >>              
    >>              return config;
    >>          }
    >>          
    >>          @Bean
    >>          public ShardedJedisPool getPool() {
    >>              String[] listNode = nodes.split(", ");
    >>              
    >>              List<JedisShardInfo> listInfo = new ArrayList<JedisShardInfo>();
    >>              
    >>              for (String node : listNode) {
    >>                  if ("".equals(node)) {
    >>                      System.out.println("RedisConfig.getPool(), empty node");
    >>                      continue;
    >>                  }
    >>                  
    >>                  String[] host = node.split(":");
    >>                  if (host.length != 2) {
    >>                      throw new RuntimeException();
    >>                  }
    >>                  
    >>                  JedisShardInfo info = new JedisShardInfo(host[0], host[1]);
    >>                  listInfo.add(info);
    >>              }
    >>              
    >>              ShardedJedisPool pool = new ShardedJedisPool(getConfig(), listInfo);
    >>              return pool;
    >>          }
    >>      }
    >> </details>
    > 上面的代码还需要用到全局配置文件
    >> <details>
    >> <summary><mark><font color=darkred>application.properties</font></mark></summary>
    >> 
    >>      # redis 配置信息
    >>      
    >>      # 节点信息
    >>      spring.redis.nodes = 192.168.142.128:6379, 192.168.142.128:6380, 192.168.142.128:6381
    >>      # 配置对象时需要的配置信息
    >>      spring.redis.pool.max-idle = 8
    >>      spring.redis.pool.min-idle = 1
    >>      spring.redis.pool.max-total = 200
    >>      spring.redis.pool.max-wait = 5000
    >> </details>


- ## 连接哨兵模式主从备份redis 
    > - demo
    >> <details>
    >> <summary><mark><font color=darkred>哨兵模式demo</font></mark></summary>
    >> 
    >>      @Test
    >>      public void sentinel(){
    >>          //多个哨兵之间没有监控关系，顺序遍历，哪个能通就走哪个，不通，再检查下一个哨兵
    >>          Set<String> sentinels = new HashSet<String>();
    >>          sentinels.add(new HostAndPort("10.42.170.247",26382).toString());
    >>          sentinels.add(new HostAndPort("10.42.170.247",26383).toString());
    >>          sentinels.add(new HostAndPort("10.42.170.247",26383).toString());
    >>          
    >>          //mymaster是在sentinel.conf中配置的名称
    >>          //sentinel monitor mymaster 192.168.163.200 6380 1
    >>          JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
    >>          System.out.println("当前master：" + pool.getCurrentHostMaster());
    >>          
    >>          Jedis jedis = pool.getResource();
    >>          //jedis.auth("123456");
    >>      
    >>          System.out.println(jedis.get("num"));
    >>          pool.returnResource(jedis);   
    >>          
    >>          pool.destroy();
    >>          System.out.println("ok");
    >>      }
    >> </details>







