package en.tedu.extend;

/**
 * 子类
 * 	特有：workId
 * @author suyh
 *
 */
public class Teacher extends Person {
	int workId;
	
	public void getTeach() {
		workId = 001;
		name = "张三";
		age = 33;
		gender = "女";
	}
}
