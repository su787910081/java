package cn.tedu.demo.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Iterator {
	public static void main(String[] args) {
		// demo01();
		demo02();
	}
	
	public static void demo01()
	{
		Collection<String> col = new ArrayList<String>();
		col.add("java");
		col.add("web");
		col.add(".net");
		java.util.Iterator<String> itr = col.iterator();
		// itr.next();
		// System.out.println(itr.next());

		// 对集合进行遍历，如果遍历到的元素包含字母a 将此元素从集合中移除
		while (itr.hasNext()) {
			String str = itr.next();
			// 对str 进行是否有字符a 判断
			int nIndex = str.indexOf('a');
			if (nIndex >= 0) {
				itr.remove();
			}
		}

		System.out.println(col);

		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("web");
		list.add(".net");
		
		java.util.Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			String str = it.next();
			if (str.indexOf('a') >= 0)
			{
				list.remove(0);	// 异常，迭代过程中使用了集合中的remove() 方法。
			}
		}
	}
	
	public static void demo02()
	{
		Collection<String> col = new ArrayList<String>();
		col.add("java");
		col.add("web");
		col.add(".net");

		for (String str : col)
		{
			System.out.println(str);
		}
	}
}

