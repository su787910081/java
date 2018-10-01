package cn.tedu.wrapper;

public class AutoBoxingDemo {
	public static void main(String[] args) {
		int i = 4;
		Integer x = i;
		System.out.println(x);
		
		Integer ite = new Integer(45);
		int m = ite;
		System.out.println(m);
	}
}
