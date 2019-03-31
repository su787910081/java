package cn.tedu.project.service.imple;

import cn.tedu.project.dao.MessageDao;
import cn.tedu.project.dao.impl.MessageDaoImpl;
import cn.tedu.project.entity.Message;
import cn.tedu.project.service.MessageService;

public class MessageServiceImpl implements MessageService {
	MessageDao md = new MessageDaoImpl();

	@Override
	public void saveMessage(Message msg) {
		md.insertMsg(msg);
	}
	
	public static void main(String[] args) {
		MessageService ms = new MessageServiceImpl();
		
		Message msg = new Message();
		msg.setContent("suyh second context, at MessageServiceImpl.");
		ms.saveMessage(msg);
		
		System.out.println("MessageServiceImpl.main()");
	}

}
