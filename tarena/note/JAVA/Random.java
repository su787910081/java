生成随机数
	1. Math.random(); 
		0 <= 随机数 < 1
	2. Random 类
		Random rd = new Random();
		rd.nextInt(1000);
        Random rd2 = new Random(47);    // 给定一个随机种子
        rd.nextInt(20); // 18   随机数在[0, 20) 区间。如果不指定大小，则有可能出现一个负数





















