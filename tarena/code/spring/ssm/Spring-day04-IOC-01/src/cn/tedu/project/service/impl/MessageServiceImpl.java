package cn.tedu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.project.dao.MessageDao;
import cn.tedu.project.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

//	@Autowired
	private MessageDao msgDao;
	
//	@Autowired
	public MessageServiceImpl() {
		System.out.println("MessageServiceImpl.MessageServiceImpl()");
	}
	
	@Autowired
	public MessageServiceImpl(MessageDao msgDao) {
		this.msgDao = msgDao;
		System.out.println("MessageServiceImpl.MessageServiceImpl(MessageDao msgDao)");
	}
	
	@Override
	public void saveMsg(String msg) {
		msgDao.insertMsg(msg);
	}
}
