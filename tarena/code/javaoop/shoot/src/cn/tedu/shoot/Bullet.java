package cn.tedu.shoot;

/**
 * 子弹
 * @author suyh
 *
 */
public class Bullet {
	int width;
	int height;
	int x;
	int y;
	int step;
	public Bullet(int x, int y) {
		width = 8;
		height = 14;
		this.x = x;
		this.y = y;
		step = 3;
	}
	
	// 移动 为
	public void step() {
		System.out.println("子弹移动 了" + step + "个像素.");
	}
	
	
	
}
