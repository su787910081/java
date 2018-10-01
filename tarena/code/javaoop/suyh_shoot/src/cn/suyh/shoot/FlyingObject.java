package cn.suyh.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

// 飞行物
public abstract class FlyingObject {
	public static final int LIFE = 0;	// 活着的
	public static final int DEAD = 1;	// 死亡
	public static final int REMOVE = 2;	// 可以删除
	
	protected int state = LIFE;	// 当前状态(默认为活着的)
	
	protected int width;	// 宽
	protected int height;	// 高
	protected int x;
	protected int y;
	
	// 默认构造
	public FlyingObject(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	// 专门给敌人留的(Airplane、BigAriplane、Bee)
	public FlyingObject(int width, int height) {
		this.width = width;
		this.height = height;
		Random rand = new Random();	// 随机数对象
		x = rand.nextInt(World.WIDTH - this.width);
		y = -this.height;	// y: 负的小敌机的高
	}
	
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));
			return img;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
	
	// 飞行物移动
	public abstract void step();
	
	public abstract BufferedImage getImage();
	
	// 画对象 g:画笔
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
	
	public boolean isLife() {
		return state == LIFE;
	}
	
	public boolean isDead() {
		return state == DEAD;
	}
	
	public boolean isRemove() {
		return state == REMOVE;
	}
	
	// 判断飞行物是否越界
	public abstract boolean outOfBounds();
	
	// 检测敌人与子弹和英雄机的碰撞this: 敌人 other: 子弹和英雄机
	public boolean hit(FlyingObject other) {
		int x1 = this.x - other.width;	// x1: 敌人的x - 子弹的宽
		int x2 = this.x + this.width;	// x2: 敌人的x + 敌人的宽
		int y1 = this.y - other.height;	// y1: 敌人的y - 子弹的高
		int y2 = this.y + this.height;	// y2: 敌人的y + 敌人的高
		int x = other.x;	// x: 子弹的x
		int y = other.y;	// y: 子弹的y
		
		// x 在x1 与x2 之间，并且，y 在y1 与y2 之间，即为撞上了
		return x >= x1 && x <= x2 &&
				y >= y1 && y <= y2;
	}
	
	public void goDead() {
		state = DEAD;
	}
}
