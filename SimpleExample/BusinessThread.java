

public class Main {
    public static void main(String[] args) {
        BusinessThread thread = new BusinessThread();
        
        thread.start();
        
        Main m = new Main();
        
        try {
            Thread.sleep(1);
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
        
        thread.add(func);
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
        queue = new LinkedList<IFunc>();
        
        while (true) {
            IFunc f = null;
            synchronoized (queue) {
                f = queue.poll();
            }
            
            if (f != null) {
                f.func(str);
            }
            
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                // do nothing
            }
        }
    }
    
    public void add(IFunc func) {
        synchronoized (queue) {
            queue.add(func);
        }
    }

    Queue<IFunc> queue;
    String str = "string";
}

interface IFunc {
    void func(String str);
}











