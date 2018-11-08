

public class Main {
    public static void main(String[] args) {
        BusinessThread thread = new BusinessThread();
        
        thread.start();
        
        Main m = new Main();
        
        try {
            System.out.println(Thread.currentThread().getname() + " sleep 10 seconds");
            Thread.sleep(10000);
        } catch (Exception e) {
            // do nothing
        }
        
        // 以匿名内部类的形式添加
        IFunc func = new IFunc() {
            @Override
            public void func(String str) {
                m.func(str, 5);
            }
        };
        
        // 以lambda 表达式的形式添加
        IFunc func2 = (String str) -> { m.func(str, 11); };
        
        System.out.println(Thread.currentThread().getname() + " add first task");
        thread.add(func);
        
        try {
            System.out.println(Thread.currentThread().getname() + " sleep 5 seconds");
            Thread.sleep(5000);
        } catch (Exception e) {
            // do nothing
        }
        
        System.out.println(Thread.currentThread().getname() + " add seconds task");
        thread.add(func2);
    }
    
    public void func(String str, int n) {
        System.out.println(str + ": " + n);
    }
}

import java.util.LinkedList;
import java.util.Queue;

class BusinessThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread begin...");
        queue = new LinkedList<IFunc>();
        str = Thread.getCurrentThread().getName();
        
        while (true) {
            IFunc f = null;
            synchronoized (queue) {
                while (queue.size() == 0) {
                    try {
                        // 这里要进入到有锁的对象休眠，否则会报错
                        queue.wait();
                    } catch (InterruptedException e) {
                        continue;
                    }
                    
                    System.out.println("notify come on.");
                }
                
                f = queue.poll();
            }
            
            if (f != null) {
                f.func(str);
            }
        }
    }
    
    public void add(IFunc func) {
        synchronoized (queue) {
            queue.add(func);
            queue.notigyAll(); // 这里一样要使用获取锁的对象，来进行通知，否则也是报错，与wait() 相对应
        }
    }

    Queue<IFunc> queue;
    String str;
}

interface IFunc {
    void func(String str);
}











