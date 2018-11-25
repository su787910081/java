package com.tedu.dbpool;

// 声明一个Phone 接口
interface Phone {
    public void call();
    public void message();
}

class IPhone implements Phone {

    @Override
    public void call() {
        System.out.println("手机可以打电话了");
    }

    @Override
    public void message() {
        System.out.println("手机可以发短信了");
    }
    
}

// 写一个类 RingIPhone 继承 IPhone 类
// 重写call 方法为吸彩铃
class RingIPhone extends IPhone {
    @Override
    public void call() {
        System.out.println("手机可以听彩铃了...");
    }
}


public class Demo {
    public static void main(String[] args) {
        Phone iphone = new IPhone();
        
        iphone.call();
        iphone.message();
        // 我想改造iphone 对象上的call 方法，改为听彩铃，而不是打电话
        
        Phone ringIPone = new RingIPhone();
        
        ringIPone.call();
    }
}
