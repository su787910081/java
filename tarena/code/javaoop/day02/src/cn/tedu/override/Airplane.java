package cn.tedu.override;

public class Airplane extends FlyingObject {

	public void step() {
		System.out.println("双向移动的。");
		System.out.println("x方向每次移动1 个像素。");
		System.out.println("y方向每次移动2 个像素。");
	}
}
