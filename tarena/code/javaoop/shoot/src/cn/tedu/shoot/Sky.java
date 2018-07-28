package cn.tedu.shoot;

/**
 * 天空
 * @author suyh
 *
 */
public class Sky extends FlyingObject {
	int step;
	int y1;	// 第二张图片的y 坐标
	public Sky() {
		width = 400;
		height = 700;
		x = 0;
		y = 0;
		y1 = -height;
		step = 1;
	}
	
	// 移动行为
//	public void step() {
//		System.out.println("sky 移动了" + step + " 个像素.");
//	}
	
}
