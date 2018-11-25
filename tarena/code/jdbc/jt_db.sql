
drop database  if exists jt_db;

create database jt_db character set utf8;

use jt_db;

create table account(
	id int primary key auto_increment, 
    name varchar(10),
    money double
);

-- 4. 在表中插入3 条记录
insert into account values(null, 'tom',  1000);
insert into account values(null, 'andy', 1000);
insert into account values(null, 'tony', 1000);
insert into account values(null, '张三', 1000);

-- 5. 在库中创建 user 表
create table user(
	id int primary key auto_increment,
    username varchar(50),
    password varchar(50)
);

-- 6. 在user 表中，插入2 条记录
insert into user values(null, 'zhangsan', '123');
insert into user values(null, 'lisi', '123');


show tables;


select * from account;

update account set money=999;



show variables like 'char%';


show create database jt_db;

drop table batch;

select * from batch;

