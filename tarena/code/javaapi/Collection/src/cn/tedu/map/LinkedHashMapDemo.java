package cn.tedu.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap 有序的Map
 * 底层维护着一个双向循环链表，记录了迭代顺序，及存放顺序。即：怎么放进去 的就可以怎么取出去。并非从小到大的排序。排
 * @author suyh
 *
 */
public class LinkedHashMapDemo {
	public static void main(String[] args) {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>(); 
	}
}
