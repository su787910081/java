package cn.tedu.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 用户输入类名，根据类名找互此类下所有以test 开头的方法，并让他执行
 * 
 * @author suyh
 *
 */
public class Junit3Demo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("请输入类名: ");

        Scanner sc = new Scanner(System.in);

        String className = sc.nextLine();
        Class cls = Class.forName(className);
        Object obj = cls.newInstance();
        Method[] methods = cls.getDeclaredMethods();

        // 遍历所有方法，判断方法是否以test 开头，如果是则执行此方法
        for (Method m : methods) {
            if (m.getName().startsWith("test")) {
                // 执行此方法
                Object val = m.invoke(obj); // 参数一：该方法所在的对象, 参数二：参数列表
                System.out.println(val);
            }
        }

        sc.close();
    }
}

class Coo {
    public void testA() {
        System.out.println("执行A方法");
    }

    public void methodB() {
        System.out.println("执行B方法");
    }

    public int testC() {
        return 1;
    }
}
