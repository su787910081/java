package cn.tedu.shoot;

/**
 * 大敌机
 * @author suyh
 *
 */
public class BigAirplane {
	int width;
	int height;
	int x;
	int y;
	int step;
	
	public BigAirplane() {
		width = 69;
		height = 99;
		x = (int)(Math.random() * (400  - width));
		y = -height;
		step = 2;
	}
	
	// 移动行为
	public void step() {
		System.out.println("大敌机移动了: " + step + "个像素");
	}
}
