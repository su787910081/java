package cn.tedu.project.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tedu.project.dao.MessageDao;

/**
 * 数据持久层对象(封装JDBC操作的对象)一般会使用 @Repository 注解修饰
 * @author suyh
 *
 */
@Repository
public class MessageDaoImpl implements MessageDao {
	public MessageDaoImpl() {
		System.out.println("MessageDaoImpl.MessageDaoImpl()");
	}

	@Override
	public void insertMsg(String msg) {
		System.out.println("MessageDaoImpl.insertMsg()" + msg);
	}

}
