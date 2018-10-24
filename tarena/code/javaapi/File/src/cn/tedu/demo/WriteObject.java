package cn.tedu.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WriteObject {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// demo01();
		// demo02();
		// demo03();
		// demo04();
	}

	public static void demo01() throws IOException {
		Person person = new Person(1, "aa", 12, "老好人！");
		System.out.println(person);

		FileOutputStream fos = new FileOutputStream("person.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(person);

		oos.close();
		fos.close();
	}

	public static void demo02() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("person.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		Person person = (Person) ois.readObject();
		System.out.println(person);

		ois.close();
		fis.close();
	}

	public static void demo03() throws IOException, ClassNotFoundException {
		Student stu = new Student();
		stu.setScoolId(1001);
		Person person = new Person(1, "aa", 23, null);
		stu.setPerson(person);

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
		oos.writeObject(stu);
		oos.close();
	}

	public static void demo04() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("student.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Student stu = (Student) ois.readObject();
		System.out.println(stu);
		ois.close();
		fis.close();
	}
}

class Person implements Serializable {
	private static final long serialVersionUID = -5229031435203202100L;

	private int id;
	private String name;
	private int age;
	private transient String desc; // transient 关键字，序列化的时候忽略该字段

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", desc=" + desc + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Person(int id, String name, int age, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.desc = desc;
	}

	public Person() {
		super();
	}

}

class Student implements Serializable {
	private static final long serialVersionUID = 8013489758010178297L;

	private Person person;
	private int scoolId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + scoolId;
		return result;
	}

	public int getScoolId() {
		return scoolId;
	}

	public void setScoolId(int scoolId) {
		this.scoolId = scoolId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (scoolId != other.scoolId)
			return false;
		return true;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [person=" + person + ", scoolId=" + scoolId + "]";
	}
}
