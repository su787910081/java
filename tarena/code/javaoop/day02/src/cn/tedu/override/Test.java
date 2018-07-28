package cn.tedu.override;

public class Test {
	public static void main(String[] args) {
		// 创建Bee, airplane 对象，并实现移动功能
		FlyingObject bee = new Bee();
		bee.step();
		
		FlyingObject airplane = new Airplane();
		airplane.step();
	}
}






