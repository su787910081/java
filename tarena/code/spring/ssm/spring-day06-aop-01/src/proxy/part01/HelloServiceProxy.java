package proxy.part01;

public class HelloServiceProxy implements HelloService {

	private HelloService hs;
	
	public HelloServiceProxy() {
	}
	public HelloServiceProxy(HelloService helloService) {
		hs = helloService;
	}
	
	public void setHelloService(HelloService hs) {
		this.hs = hs;
	}
	
	@Override
	public void sayHello(String msg) {
		hs.sayHello(msg);
	}

}
