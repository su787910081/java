package cn.tedu.equals;

public class Student {
	private int schooldId;
	private String name;
	private int age;
	public Student() {
		super();
	}
	public Student(int schooldId, String name, int age) {
		super();
		this.schooldId = schooldId;
		this.name = name;
		this.age = age;
	}
	public int getSchooldId() {
		return schooldId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + schooldId;
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
		Student other = (Student) obj;
		if (schooldId != other.schooldId)
			return false;
		return true;
	}
	public void setSchooldId(int schooldId) {
		this.schooldId = schooldId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [schooldId=" + schooldId + ", name=" + name + ", age=" + age + "]";
	}
	
}
