package proxy.suyh.part01;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

interface IMsg {
	void sendMsg(String msg);
}
class MsgService implements IMsg {
	public void sendMsg(String msg) {
		System.out.println("send msg: " + msg);
	}
}
public class TestProxy02 {
	public static void main(String[] args) {
		MsgService msgService = new MsgService();
		
		Enhancer e = new Enhancer();
		e.setClassLoader(msgService.getClass().getClassLoader());
//		e.setSuperclass(msgService.getClass());
		e.setInterfaces(msgService.getClass().getInterfaces());
		e.setCallback(new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("begin: ");
				Object result = method.invoke(msgService, args);
				System.out.println("end: ");
				return result;
			}
		});
		
		IMsg proxy = (IMsg)e.create();
		proxy.sendMsg("hello cglib");
		
		
	}
}
