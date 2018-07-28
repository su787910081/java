package en.tedu.extend;

/**
 * 子类
 * 	特有：学号
 * @author suyh
 *
 */
public class Student extends Person {
	int schoolId;
	
	public void getStudent() {
		schoolId = 0001;
		name = "王小二";
		age = 22;
		gender = "男";
	}
}
