package cn.tedu.shoot;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 将其他所有对象都置于这个界面上
 * @author suyh
 *
 */
public class World extends JPanel {
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
		
		// 将敌方机都放入一个FlyingObject 数组中
		FlyingObject[] enemies = new FlyingObject[9];
		for (int i = 0; i < 3; i++) {
			enemies[3 * i + 0] = new Airplane();
			enemies[3 * i + 1] = new Bee();
			enemies[3 * i + 2] = new BigAirplane();
		}
		
		Bullet[] bullets = new Bullet[3];
		bullets[0] = new Bullet(100, 210);
		bullets[1] = new Bullet(100, 240);
		bullets[2] = new Bullet(100, 270);
		
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("飞机大战");
		World world = new World();
		
		frame.add(world);
		
		// 设定窗口默认关闭行为 - 如果不设置这个，那么关闭窗口后，程序不会结束。
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口大小
		frame.setSize(400, 700);
		frame.setLocationRelativeTo(null);
		// 让窗体显示，默认JFrame 创建出来 是不显示出来的，需要显示调用。
		frame.setVisible(true);
		
		world.action();
	}
}
