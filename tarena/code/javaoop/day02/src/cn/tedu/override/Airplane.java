package cn.tedu.override;

public class Airplane extends FlyingObject {

	public void step() {
		System.out.println("纵向单向移动的");
		System.out.println("纵向每次移动2个像素");
	}
}
