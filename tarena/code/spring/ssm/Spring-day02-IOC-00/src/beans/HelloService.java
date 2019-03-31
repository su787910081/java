package beans;

public class HelloService {
	public HelloService() {
		System.out.println("HelloService.HelloService()");
	}
	
	public void init() {
		System.out.println("init()");
	}
	
	public void doDestroy() {
		System.out.println("HelloService.doDestroy()");
	}
	
	
}
