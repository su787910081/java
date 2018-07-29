package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import java.util.Random;
/** 大敌机: 是飞行物，也是敌人能得分 */
public class BigAirplane extends FlyingObject implements Enemy {
	private static BufferedImage[] images; //图片数组
	static{
		images = new BufferedImage[5];
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("bigplane"+i+".png");
		}
	}
	
	private int speed;	//移动速度
	/** 构造方法 */
	public BigAirplane(){
		super(69,99);
		speed = 2;
	}
	
	/** 大敌机移动 */
	public void step(){
		y+=speed; //y+(向下)
	}
	
	int deadIndex = 1; //死了的下标
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){ //10毫秒走一次
		if(isLife()){ //若活着呢
			return images[0]; //返回第1张图片
		}else if(isDead()){ //若死了呢
			BufferedImage img = images[deadIndex++]; //从第2张图片开始
			if(deadIndex==images.length){ //当下标为数组的长度
				state = REMOVE; //则修改当前状态为可以删除的
			}
			return img;
		}
		return null;
	}
	
	/** 重写outOfBounds()判断是否越界 */
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT; //大敌机的y>=窗口的高，即为越界了
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 3; //打掉一个大敌机得3分
	}
	
}











