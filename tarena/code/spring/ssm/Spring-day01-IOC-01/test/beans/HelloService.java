package beans;

public class HelloService {
	public HelloService() {}
	public HelloService(String msg) {
		try {
			Class c = Class.forName("beans.HelloService");
			c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
