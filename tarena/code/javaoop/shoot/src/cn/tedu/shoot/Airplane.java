package cn.tedu.shoot;

/**
 * 小敌机
 * @author suyh
 *
 */
public class Airplane extends FlyingObject {
	int step;	// 移动单位
	
	
	public Airplane() {
		width = 49;
		height = 36;
		x = (int)(Math.random() * (400  - width));
		y = -height;
		step = 2;
	}
	
//	// 移动行为
//	public void step() {
//		System.out.println("小敌机移动了: " + step + "个像素");
//	}
	
	
}
