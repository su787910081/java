package proxy.part01;

public class TestProxy01 {
	public static void main(String[] args) {
		HelloServiceImpl helloService = new HelloServiceImpl();
		HelloServiceProxy helloServiceProxy = new HelloServiceProxy();
		helloServiceProxy.setHelloService(helloService);
		helloServiceProxy.sayHello("CGB");
	}
}
