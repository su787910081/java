package cn.tedu.reflact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReflectDemo02 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		Iterator<String> it = list.iterator();
		Class cls = it.getClass();
		System.out.println(cls);
	}
}
