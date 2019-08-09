-- 存储过程是没有返回值的(而函数是可以有返回值的)
-- 存储过程是属于某一个库的。它是保存在某一个数据库中的。并不是全局的。
-- 会话变量
	-- 一次会话过程中，可以设置一些变量保存数据
	-- set @name='张三' 
		-- @ 符号指会话变量
		-- @@ 表示全局变量，不能任意设置，指的是系统变量
		-- 全局变量也可以直接使用，不加@@ 也是一样的效果
-- 显示变量的值
	-- select @name;

-- 创建存储过程
	-- mysql 的存储过程不能对表进行操作
	-- 
	delimiter // -- 设置语句的结束符号为 "//"
	drop procedure if exits p1;
	// 
	
	create procedure p1()
	begin

	end;
	//
	delimiter ;





-- 创建存储过程
DELIMITER $$　　#将语句的结束符号从分号;临时改为两个$$(可以是自定义)

CREATE PROCEDURE porcSuyhTest(IN conf_no INTEGER)
BEGIN
	IF conf_no = 1 THEN
		INSERT INTO conf(note) VALUES ('conf_01');
    END IF;
END
$$

DELIMITER ;


-- 删除存储过程
DROP PROCEDURE IF EXISTS porcSuyhTest; -- 没有括号() 

-- 查看存储过程或者函数
	查看存储过程：	SHOW   CREATE  PROCEDURE  sp_name
	查看函数：		SHOW   CREATE  FUNCTION  sp_name

-- 查看存储过程
	方法一：	select `name` from mysql.proc where db = 'your_db_name' and `type` = 'PROCEDURE';
	方法二：	show procedure status;



