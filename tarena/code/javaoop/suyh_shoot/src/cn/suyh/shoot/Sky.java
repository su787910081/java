package cn.suyh.shoot;

public class Sky extends FlyingObject {
	private static BufferedImage imgae;
	
	static {
		image = loadImage("background.png");
	}

	private int step;
	private int y1;
	
	public void step() {
		y += step;
		y1 += step;
		
		if (y >= this.height) {	// 窗口最下面的边界
			y = -this.height;	// 回到最上面的边界
		}
		if (y1 >= this.height) {
			y1 = -this.height;
		}
	}
}
