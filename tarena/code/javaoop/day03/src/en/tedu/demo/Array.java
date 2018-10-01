package en.tedu.demo;

public class Array {
	public static void main(String[] args) {
		String[] arr = null;
		arr = new String[3];
		arr[0] = "0";
		arr[2] = "2";
		if (arr[1] == null) {
			System.out.println("arr[1] = null.");
		}
		
		String a1 = arr[1];
		a1 = "1";
		System.out.println("arr[1]: " + arr[1]);
		System.out.println("a1: " + a1);
		arr[1] = a1;
		System.out.println("arr[1]: " + arr[1]);
		
		String a = "string";
		a = null;
		if (a instanceof String) {
			System.out.println("a is String type.");
		} else {
			System.out.println("a is not String type.");
		}
	}
}
