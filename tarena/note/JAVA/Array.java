数组的声明：
	int[] arr;
	int arr[];
	// 数组的声明只有上面的两种，初始化数组的时候不允许在初始化的时候指定
	int[4] arr;	// 这样的声明是错误的。
	int arr[4];	// 这样的声明也是错误的。

数组的初始化：
	int[] arr = { 10, 20, 30 };	// 静态初始化
	int[] arr = new int[2];	// 动态初始化	给定长度但不赋修士，元素会默认初始化
	int[] arr = new int[]{ 10, 20 };	// 静态初始化
	int[] arr = null;	// 将这个数组初始化为null，然后就可以使用 arr == null 进行判断

数组的长度：
	arr.length 数组有一个属性length 直接使用就可以得到长度，返回值是int 类型

foreach 循环
	int[] ary = new int[10];
	for (int m : ary) {
	}

数组排序
	import java.util.Arrays;
	String[] ary = {"d", "a", "c", "b"};
	Arrays.sort(ary);
	Arrays.toString(ary);
	
数组的复制
	Arrays.copyOf() 数组复制
	// 数组的扩容，此扩容并不是真正的扩容
	ary = Arrays.copyOf(ary, 5);

	System.arraycopy()
		arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
		src - 源数组
		srcPos - 源数组中的起始位置
		dest - 目标数组
		destPos - 目标数组中的起始位置
		length - 复制的元素个数

多维数组
	发给维数组中的二组中的元素个数可以不同















