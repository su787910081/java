package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 子弹: 是飞行物 */
public class Bullet extends FlyingObject {
	private static BufferedImage image; //图片
	static{
		image = loadImage("bullet.png");
	}
	
	private int speed; //移动速度
	/** 构造方法  x,y:随英雄机的位置而不同*/
	public Bullet(int x,int y){
		super(8,14,x,y);
		speed = 3;
	}
	
	/** 子弹移动 */
	public void step(){
		y-=speed; //y-(向上)
	}
	
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){
		if(isLife()){ //如果活着呢
			return image; //返回image图片
		}else if(isDead()){ //如果死了的
			state = REMOVE; //将对象状态修改为可以删除的
		}
		return null;
	}
	
	/** 重写outOfBounds()判断是否越界 */
	public boolean outOfBounds(){
		return this.y<=-this.height; //子弹的y<=负的子弹的高，即为越界了
	}
	
}
















