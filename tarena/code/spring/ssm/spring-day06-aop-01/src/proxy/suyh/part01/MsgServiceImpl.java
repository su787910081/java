package proxy.suyh.part01;

public class MsgServiceImpl implements IMsgService {

	@Override
	public void sendMsg(String msg) {
		System.out.println("send msg: " + msg);
	}

}
