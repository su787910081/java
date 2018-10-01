package cn.suyh.shoot;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel {
	public static final int WIDTH = 400;	// 窗口宽
	public static final int HEIGHT = 700; 	// 窗口高
	
	public static final int START = 0;	// 启动状态
	public static final int RUNNING = 1;	// 运行状态
	public static final int PAUSE = 2;	// 暂停状态
	public static final int GAME_OVER = 3;	 // 游戏结束
	private int state = START;	// 当前状态(默认为启动状态)
	
	private static BufferedImage start;	// 启动图
	private static BufferedImage pause;	// 暂停图
	private static BufferedImage gameover;	// 结束图
	
	static {
		start = FlyingObject.loadImage("start.png");
		pause = FlyingObject.loadImage("pause.png");
		gameover = FlyingObject.loadImage("gameover.pgn");
	}

	private Sky sky = new Sky();
	private Hero hero = new Hero();
	private FlyingObject[] enemies = {};	// 敌人数组(小敌机 + 大敌机 + 小蜜蜂)
	private Bullet[] bullets = {};	// 子弹数组
	
	// 创建敌人
	public FlyingObject nextOne() {
		Random rand = new Random();	// 创建随机数对象
		int type = rand.nextInt(20);	// 1 ~ 19
		if (type < 7) {
			return new Airplane(); 
		} else if (type < 14) {
			return new BigAirPlance();
		} else {
			return new Bee();
		}
	}
	
	int enterIndex = 0;	// 敌人入场计数
	// 敌人入场
	public void enterAction() {	// 10 毫秒走一次
		enterIndex++;
		if (enterIndex % 40 == 0) {	// 每400(40 * 10) 毫秒走一次
			FlyingObject obj = nextOne();
			enemies = Arrays.copyOf(enemies, enemies.length + 1);	// 扩容
			enemies[enemies.length - 1] = obj;	// 将敌人对象赋值 给enemies 的最后一个位置
		}
	}
	
	// 飞行物移动
	public void stepAction() {	// 10 毫秒走一次
		sky.step();	// 天空移动
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].step();
		}
		for (int i = 0; i < bullets.length; ++i) {
			bullets[i].step();
		}
	}
	
	int shootIndex = 0;  // 发射子弹计数
	// 子弹入场
	public void shootAction() {
		shootIndex++;	// 每10 毫秒 增1 
		if (shootIndex % 30 == 0) {
			Bullet[] bs = hero.shoot();	// 获取英雄机发射的子弹对象
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);	// 数组的追加
		}
	}
	
	public void outOfBoundsAction() {	// 10 毫秒走一次
		int index = 0;	// 1) 不越界敌人数组下标 2) 不越界敌人个数
		FlyingObject[] enemyLives = new FlyingObject[enemies.length];	// 不越界敌人数组
		for (int i = 0; i < enemies.length; ++i) { // 遍历所有敌人
			FlyingObject f = enemies[i];	// 获取每一个敌人
			if (!f.outOfBounds() && !f.isRemove()) {
				enemyLives[index] = f;	// 将不越界的敌人对象添加到不越界敌人数组中
				index++;	// 1) 不越界敌人数组下标增1 2) 不越界敌人个数增1
			}
		}
		
		enemies = Arrays.copyOf(enemyLives, index);
		
		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];// 不越界子弹数组
		for (int i = 0; i < bullets.length; ++i) {
			Bullet b = bullets[i]; 	// 获取每一个子弹
			if (!b.outOfBounds() && !b.isRemove()) {
				bulletLives[index] = b;	// 将不越界子弹对象添加到不越界子弹数组中
				index++;	// 1) 不越界子弹数组下标增1 2) 不越界子弹个数增1
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);	// 将不越界子弹数组复制到bullets中，index 有几个，则bullets 的长度就为几个
	}
	
	int score = 0;	// 玩家得分
	// 子弹与敌人的碰撞
	public void bulletBangAction() { // 10 毫秒走一次
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			for (int j = 0; j < enemies.length; j++) {
				FlyingObject f = enemies[j];	// 获取每一个敌人
				if (b.isLife() && f.isLife() && f.hit(b)) {  // 撞上了
					b.goDead();
					f.goDead();
					if (f instanceof Enemy) {	// 若被撞对象是敌人
						Enemy e = (Enemy)f;	// 将被撞对象强转为敌人
						score += e.getScore();	// 玩家得分
					}
					if (f instanceof Award) { // 若被撞对象是奖励
						Award a = (Award)f;
						int type = a.getType();
						Switch (type) {
							case Award.DOUBLE_FIRE:
								hero.addDoubleFire();
								break;
							case Award.LIFE:
								hero.addLife();
								break;
						}
					}
				}
			}
		}
	}
	
	// 英雄机与敌人的碰撞
	public void heroBangAction() {
		for (int i = 0; i < enemies.length; ++i) {
			FlyingObject f = enemies[i];
			if (f.isLife() && f.hit(hero)) {
				f.goDead();
				hero.subtractLife();	// 英雄机减命
				hero.clearDoubleFire();	// 英雄机清空火力值
			}
		}
	}
	
	// 检测游戏结束
	public void checkGameOverAction() {	// 10 毫秒走一次
		if (hero.getLife() <= 0) {
			state = GAME_OVER;
		}
	}
	
	// 程序启动执行
	public void action() {
		MouseAdapter l = new MouseAdapter() {
			// 重写mouseMoved() 鼠标移动事件
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING) {
					int x = e.getX();// 获取鼠标 的x 坐标
					int y = e.getY();	// 获取鼠标 的y 坐标
					hero.moveTo(x, y);	// 英雄机随着鼠标移动
				}
			}
			// 重写mouseClicked() 鼠标点击事件
			public void mouseClicked(MouseEvent e) {
				switch (state) {	// 根据当前状态做不同处理
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					score = 0;
					sky = new Sky();
					hero = new Hero();
					enemies = new FlyingObject[0];
					bullets = new Bullet[0];
					state = START;	// 修改为启动状态
					break;
				}
			}
			// 重写mouseExisted() 鼠标移动事件
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
			// 重写mouseEntered() 鼠标移入事件
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}
		};
		this.addMouseListener(l);	// 处理鼠标操作事件
		this.addMouseMotionListener(l);	// 处理鼠标 滑动事件
		
		Timer timer = new Timer();	// 创建定时器对象
		timer.schedule(new TimerTask() {
			public void run() { // 每10 个毫秒走一次 -- 寒战时干的那个事
				if (state == RUNNING) {
					enterAction();	// 敌人入场
					stepAction();	// 飞行物移动
					shootAction();	// 子弹入场(英雄机发射子弹)
					outOfBoundsAction();	// 删除越界的飞行物
					bulletBangAction();	// 子弹与敌人的碰撞
					heroBangAction();	// 英雄机与敌人的碰撞
					checkGameOverAction();	// 检测游戏结束
				}
				repaint();	// 重画(重新调用paint() 方法)
			}
		}, 10, 10);	// 定时计划
	}
	
	// 重写paint() 画图
	public void paint(Graphics g) {
		sky.paintObject(g);
		hero.paintObject(g);
		for (int i = 0; i < enemies.length; ++i) {
			enemies[i].paintObject(g);
		}
		for (int i = 0; i < bullets.length; ++i) {
			bullets[i].paintObject(g);
		}
		g.drawString("SCORE:" + score, 10, 25);	 // 画分
		g.drawString("LIFE: " + hero.getLife(), 10, 45);	// 画命
		
		switch (state) {	// 不同状态 下画不同的图
		case START:	//启动状态
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	
	publict static void main(String[] args) {
		JFrame frame = new JFrame();	// 创建一个窗口对象
		World world = new World();
		frame.add(world);	// 将画板添加到窗口中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 设置关闭窜时退出程序
		frame.setSize(WIDTH, HEIGHT);	// 设置窗口的大小
		frame.setLocationRelativeTo(null);	// 设置窗口剧中显示
		frame.setVisible(true);	// 1) 设置窗口可见，2) 尽快调用 paint()
		
		world.action();// 启动程序 的执行
	}
}
