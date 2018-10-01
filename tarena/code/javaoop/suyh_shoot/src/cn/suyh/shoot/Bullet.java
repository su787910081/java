package cn.suyh.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private static BufferedImage image;	// ͼƬ
	
	static { 
		image = loadImage("bullet.png");
	}
	
	private int step;
	
	public void step() {
		y -= step;
	}
}
