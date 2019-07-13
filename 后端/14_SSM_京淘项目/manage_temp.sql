



create database jt_sys default character set utf8;
use jt_sys;

CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='角色' ;

select * from sys_roles;

select name from sys_roles;

insert into sys_roles(id, name, note, createdTime, modifiedTime, createdUser, modifiedUser) values(null, 'suyh-04', 'suyunhong-04', now(), now(), 'admin', 'admin');
insert into sys_roles(id, name, note, createdTime, modifiedTime, createdUser, modifiedUser) values(null, 'root', 'nothing', now(), now(), 'admin', 'admin');
insert into sys_roles(id, name, note, createdTime, modifiedTime, createdUser, modifiedUser) values(null, 'John', 'note nothing', now(), now(), 'admin', 'admin');


update sys_roles set 
		name='suyh-02', note='suyunhong-02', modifiedTime=now()
		where id=24;




CREATE TABLE `sys_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100),
  `salt` varchar(50), 
`email` varchar(100),
  `mobile` varchar(100),
  `valid` tinyint(4),
  `createdTime` datetime,
  `modifiedTime` datetime,
  `createdUser` varchar(20),
  `modifiedUser` varchar(20),
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


select * from sys_users  where username like concat('%', 'suyh', "%") order by id limit 0, 10;

insert into sys_users(id, username, password, salt, email, mobile, valid, createdTime, modifiedTime, createdUser, modifiedUser)
values(null, 'suyh-02', 'suyunhong-02', 'salt', 'suyh@sina.com', '17727448330', 1, now(), now(), 'admin', 'admin');
insert into sys_users(id, username, password, salt, email, mobile, valid, createdTime, modifiedTime, createdUser, modifiedUser)
values(null, 'suyh-03', 'suyunhong-03', 'salt', 'suyh@sina.com', '17727448330', 1, now(), now(), 'admin', 'admin');
insert into sys_users(id, username, password, salt, email, mobile, valid, createdTime, modifiedTime, createdUser, modifiedUser)
values(null, 'suyh-04', 'suyunhong-04', 'salt', 'suyh@sina.com', '17727448330', 1, now(), now(), 'admin', 'admin');
insert into sys_users(id, username, password, salt, email, mobile, valid, createdTime, modifiedTime, createdUser, modifiedUser)
values(null, 'jhon', 'jhon', 'salt', 'suyh@sina.com', '17727448330', 1, now(), now(), 'admin', 'admin');
insert into sys_users(id, username, password, salt, email, mobile, valid, createdTime, modifiedTime, createdUser, modifiedUser)
values(null, 'tedu', 'terena', 'salt', 'suyh@sina.com', '17727448330', 1, now(), now(), 'admin', 'admin');


select * from sys_users;
delete from sys_users where password='tttttttt';



CREATE TABLE `sys_roles_users` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `role_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


select * from sys_roles_users;



insert into sys_roles_users(user_id, role_id) values(1, 1), (1, 2);

delete  from sys_roles_users where user_id = 1;

commit;


select * from sys_users;

select * from sys_roles_users;



delete from sys_users;




CREATE TABLE `sys_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `type` int(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `permission` varchar(500) DEFAULT NULL COMMENT '授权(如：role:create)',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;


insert into sys_menus(id, name, url, type, sort, note, parentId, permission, createdTime, modifiedTime, createdUser, modifiedUser) values(1, '系统管理', null, 1, 1, '系统管理...', 0, 'sys:view', now(), now(), 'admin', 'admin');
insert into sys_menus values(null, '用户管理', null, 1, 1, '用户管理...', 1, 'sys:view', now(), now(), 'admin', 'admin');
insert into sys_menus values(null, '角色管理', null, 1, 1, '角色管理...', 1, 'sys:view', now(), now(), 'admin', 'admin');

update sys_menus set name = '系统管理' where id = 1;
update sys_menus set note = '用户管理...' where id = 4;
update sys_menus set note = '角色管理...' where id = 5;

select * from sys_menus;


Select m.*, (select p.name from sys_menus p where p.id = m.parentId) as parentName from sys_menus m ;


select * from sys_menus;

select count(m.id) from sys_menus m, sys_menus p where m.parentId = 1 and m.parentId = p.id;
select count(parentId) from sys_menus where parentId = 2;

update sys_menus set parentId = null where id = 1;


select m.*, p.name as parentName from sys_menus m, sys_menus p where m.id = 4 and m.parentId = p.id;

 select m.*, p.name parentName 
 from sys_menus m left join sys_menus p 
 on m.parentId = p.id
 where m.id = 1;
 
 
 create table sys_roles_menus(role_id int, menu_id int, primary key(role_id, menu_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
 
 SHOW TABLES;
 
 DROP table sys_roles_menus;
 
 
 select permission 
		from sys_roles_users sru join sys_roles_menus srm join sys_menus sm
		on sru.role_id = srm.role_id and srm.menu_id = sm.id
		where sru.user_id = 14;



        