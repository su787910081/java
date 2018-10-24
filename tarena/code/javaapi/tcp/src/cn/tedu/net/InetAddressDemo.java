package cn.tedu.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress.getLocalHost() - InetAddress; getHostName() - String
 * getHostAddress() - String
 * 
 * @author suyh
 *
 */

public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		demo01();
		
		demo02();
	}
	
	public static void demo01() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr);
		String strHost = addr.getHostName();
		String strAddress = addr.getHostAddress();
		
		System.out.println(strHost);
		System.out.println(strAddress);
	}
	
	public static void demo02() {
		
	}
}
