package cn.tedu.shoot;

/**
 * 所有飞行物的父类
 * @author suyh
 *
 */
public class FlyingObject {
	int width;
	int height;
	int x; 
	int y;

	public void step() {
		System.out.println("飞行物移动了！");
	}
}
