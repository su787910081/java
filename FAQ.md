

1. 如何将前端的数组类型，通过ajax 传入spring MVC 的controller 的方法参数中?
    > 在ajax 处添加指定参数

        var checkedIds = [8, 9];
        var params = {
				"checkedIds": checkedIds
		};
		var url = "role/doDeleteObjectByArray.do";
		$.post(url, params, function(result){
            /// ...
		});
    > 在controller 中需要添加注解`@RequestParam(value = "checkedIds[]", required = false)` 注意有一个`[]`

        @RequestMapping(value = "doDeleteObjectByArray",method = RequestMethod.POST)
        @ResponseBody
        public String doDeleteObjectByArray(@RequestParam(value = "checkedIds[]", required = false)String[] checkedIds) {
            System.out.println("checkedIds: " + Arrays.toString(checkedIds));
            return "ok";
        }




百度搜索漫画讲解公私钥
https://www.cnblogs.com/shijingjing07/p/5965792.html


# 面试问题

- ## Redis
    - > Redis 缓存击穿
        - > 原因及场景
            > - 缓存穿透是指查询一个一定不存在的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。在流量大时，可能DB就挂掉了，要是有人利用不存在的key频繁攻击我们的应用，这就是漏洞。
        - > 解决方法
            > - 1. 最常见的则是采用布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被 这个bitmap拦截掉，从而避免了对底层存储系统的查询压力。
            > - 2. 另外也有一个更为简单粗暴的方法（我们采用的就是这种），如果一个查询返回的数据为空（不管是数 据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。
    - > Redis 缓存雪崩
        - > 原因及场景
            > - 缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩。
        - > 解决方案
            > - 缓存失效时的雪崩效应对底层系统的冲击非常可怕。大多数系统设计者考虑用加锁或者队列的方式保证缓存的单线程（进程）写，从而避免失效时大量的并发请求落到底层存储系统上。这里分享一个简单方案就时讲缓存失效时间分散开，比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。












