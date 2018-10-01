package cn.suyh.shoot;

import java.awt.image.BufferedImage;

public class Bee extends FlyingObject{

	private static BufferedImage[] images;
	
	static {
		images = new BufferedImage[5];
		for (int i = 0; i < images.length; ++i) {
			images[i] = loadImage("bee" + i + ".png");
		}
	}
	
	private int xStep;	// x 坐标移动速度
	private int yStep;	// y 坐移动速度
	private int awardType;	// 奖励类型(0 或者 1)
	
	public void step() {
		x += xStep;
		y += yStep;
		
		if (x >= World.WIDTH - this.width){
			xstep *= -1;
		}
	}
	
	
}
