package en.tedu.demo;

public class StudentTest {
	int var;
	String name;
	int age;
	
	public StudentTest(int var, String name, int age) {
		this.var = var;
		this.name = name;
		this.age = age;
	}

	public static void main(String[] args) {
		Student stu1 = new Student();
		stu1.name = "ÕÅÏşÀö";
		stu1.age = 12;
		stu1.gender = "Å®";
		stu1.schoolId = "001";
	}

	public StudentTest() {
		// TODO Auto-generated constructor stub
	}
}
