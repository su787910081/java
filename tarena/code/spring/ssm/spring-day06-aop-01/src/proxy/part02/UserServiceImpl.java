package proxy.part02;

public class UserServiceImpl implements UserService {

	@Override
	public void saveUser(String user) {
//		System.out.println("begin transaction");
		System.out.println("saveUser: " + user);
//		System.out.println("end transaction");
	}

}
