package cn.tedu.shoot;

/**
 * 将其他所有对象都置于这个界面上
 * @author suyh
 *
 */
public class World {
	public void action() {
		// 创建对象以及实现让对象移动
		Sky sky = new Sky();
		Hero hero = new Hero();
		Airplane airplane = new Airplane();
		BigAirplane bigAirplane = new BigAirplane();
		Bee bee = new Bee();
		Bullet bullet = new Bullet(100, 200);
		
		// 让对象都实现一次移动
		sky.step();
		airplane.step();
		bigAirplane.step();
		bee.step();
		hero.move(200, 300);
		bullet.step();
	}
	
	public static void main(String[] args) {
		World world = new World();
		world.action();
	}
}
