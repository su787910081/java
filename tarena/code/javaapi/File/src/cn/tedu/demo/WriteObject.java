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
		demo02();
	}

	public static void demo01() throws IOException {
		Person person = new Person(1, "aa");

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
}

class Person implements Serializable {
	private int id;
	private String name;

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

	@Override
	public String toString() {
		return "Persion [id=" + id + ", name=" + name + "]";
	}

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Person() {
		super();
	}

}
