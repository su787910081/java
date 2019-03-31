package cn.tedu.project.msg.service.impl;

import java.util.logging.Logger;

import cn.tedu.project.msg.dao.MessageDao;
import cn.tedu.project.msg.dao.impl.MessageDaoImpl;
import cn.tedu.project.msg.entity.Message;
import cn.tedu.project.msg.service.MessageService;

// service --> dao --> db
public class MessageServiceImpl implements MessageService {
	private MessageDao messageDao = new MessageDaoImpl();
	private Logger log = Logger.getLogger("MessageServiceImpl");

	@Override
	public void saveObject(Message msg) {
		log.info("save object start");
		messageDao.insertObject(msg);
		log.info("save object success");
	}
	
	public static void main(String[] args) {
		MessageService ms = new MessageServiceImpl();
		Message msg = new Message();
		msg.setContent("MessageServiceImpl");
		ms.saveObject(msg);
	}
}
