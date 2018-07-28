package cn.tedu.shoot;

/**
 * 小蜜蜂
 * @author suyh
 *
 */
public class Bee {
	int width;
	int height;
	int x;
	int y;
	int xstep;
	int ystep;
	public Bee() {
		width = 60;
		height = 50;
		x = (int)(Math.random() * (400  - width));
		y = -height;
		xstep = 1;
		ystep = 2;
	}
	
	public void step() {
		System.out.println("小蜜蜂横向移动了" + xstep + "个像素.");
		System.out.println("小蜜蜂纵向移动了" + ystep + "个像素.");
	}
	
}
