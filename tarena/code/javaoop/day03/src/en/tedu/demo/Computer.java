package en.tedu.demo;

public class Computer {
	private String name;
	private int price;
	private int number;
	private String cpu;
	
	
	
	public Computer() {
	}

	public Computer(String name, int price, int number, String cpu) {
		super();
		this.name = name;
		this.price = price;
		this.number = number;
		this.cpu = cpu;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	@Override
	public String toString() {
		return "Computer [name=" + name + ", price=" + price + ", number=" + number + ", cpu=" + cpu + ", getName()="
				+ getName() + ", getPrice()=" + getPrice() + ", getNumber()=" + getNumber() + ", getCpu()=" + getCpu()
				+ "]";
	}
	
	
}
