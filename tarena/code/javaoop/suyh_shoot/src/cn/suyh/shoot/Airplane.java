package cn.suyh.shoot;

public class Airplane extends FlyingObject {
	public Airplane() {
		super(49, 36);	// 调用超类的构造方法
		speed = 2;
	}
	
	public void step() {
		y += step;
	}
}
