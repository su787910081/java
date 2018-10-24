package cn.tedu.synchronizeddemo;

public class ShopTest {
	public static void main(String[] args) {
		Shop shop = new Shop();	// 同一个对象，两个线程都是同一个shop 对象，否则达不到同步的效果 即：Shop s1; Shop s2;
		
		
		Thread thread = new Thread(shop, "张丽");
		Thread thread2 = new Thread(shop, "李丽");
		
		thread.start();
		thread2.start();
	}
}
