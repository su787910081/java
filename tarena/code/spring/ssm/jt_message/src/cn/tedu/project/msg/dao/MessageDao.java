package cn.tedu.project.msg.dao;

import cn.tedu.project.msg.entity.Message;

// 数据持久层对象：封装数据库的操作逻辑
public interface MessageDao {
	
	int insertObject(Message msg);
}
