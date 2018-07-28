package cn.tedu.shoot;

/**
 * 英雄机
 * @author suyh
 *
 */
public class Hero extends FlyingObject {
	int life;	// 生命值
	int doubleFire;	// 火力值
	
	public Hero() {
		// 对数据进行初始化
		width = 97;
		height = 124;
		x = 140;
		y = 400;
		life = 3;
		doubleFire = 0;
	}
	
	// 移动行为
	public void move(int x, int y) {
		System.out.println("英雄机移动到了: " + x + ", " + y);
	}
}
