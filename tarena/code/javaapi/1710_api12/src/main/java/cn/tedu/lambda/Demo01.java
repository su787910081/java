package cn.tedu.lambda;

public class Demo01 {
    public static void main(String[] args) {
        /**
         * Lambda 实现功能性接口
         */
        // 实现功能性接口
        Foo foo = new Foo() {
            public int test(int a, int b) {
                return a + b;
            }
        };
        
        Foo f2 = (int a, int b) -> { return a + b; };
        
        System.out.println(foo.test(4, 5));
        System.out.println(f2.test(10, 100));
    }
}

// 功能性接口
interface Foo {
    int test(int a, int b);
}

