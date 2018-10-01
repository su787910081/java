package cn.suyh.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
	private static BufferedImage[] images;	// 图片数组
	
	static {
		images = new BufferedImage[6];
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("hero" + i + ".png");
		}
	}
	
	private int life; 	// 命
	private int doubleFire;	// 火力值
	
	public Hero() {
		super(97, 124, 140, 400);
		this.life = 3;	// 默认3 条命
		this.doubleFire = 0;
	}
	
	public void moveTo(int x, int y) {
		this.x = x - this.width / 2;
	}
	
	public void step() {
		
	}
	
	
}
