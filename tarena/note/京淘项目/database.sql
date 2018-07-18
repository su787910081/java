-- 1、创建jtds 数据库，使用utf8 编码
	create database jtds charset utf8;
	use jtds;
-- 2、创建 tb_item 表
	create table tb_item(
		id 			bigint(20),		-- id
		cid 		bigint(20),		-- 分类id
		brand 		varchar(50),	-- 品牌
		model 		varchar(50),	-- 型号
		title 		varchar(100), 	-- 商品名
		sell_point 	varchar(500),	-- 卖点文本
		price 		long,			-- 价格 * 100
		num 		int(10),		-- 库存量
		barcode 	varchar(30),	-- 条码
		image 		varchar(500),	-- 图片路径
		status 		tinyint(4),		-- 状态：1 正常；2 下架；3 删除
		created 	timestamp,		-- 创建时间
		updated 	timestamp		-- 更新时间
	) engine=innodb charset=utf8;

3、在tb_iter 表中插入商品数据
	insert into tb_item(id, brand, title, price, created) values(
		7, '苹果', 'iPhone x', 999999, now());

4、修改 id 是7 的商品，降价12%, 修改库存量为
	update tb_item set price = round(price * 0.88), num = 20 where id = 7;
	round(num) -- 取整四舍五入
5、删除商品7














