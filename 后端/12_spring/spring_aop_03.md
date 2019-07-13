# Spring AOP 使用注解方式代替XML 方式获取bean 对象

> 之前我们都是通过配置XML 的方式，添加对应的标签来标识一个bean 对象，以及一个AOP 对象<br>
> 现在我们改为注解的方式来代替XML 文件<br>

## 步骤
1. 不需要XML 文件了
2. 我们需要创建一个`Annotation` 
3. 在这个`Annotation` 上面添加两个注解
    > &emsp;`@EnableAspectJAutoProxy`: 表示引入切面的自动代理功能<br>
    > &emsp;`@ComponentScan("com.suyh")`: 表示自动扫描`com.suyh`包下面的类以及子类，并管理这些被注解为`spring bean` 对象的类<br>

        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.EnableAspectJAutoProxy;

        @EnableAspectJAutoProxy
        @ComponentScan("com.suyh")
        public class AppConfigAnnotation { }
4. 在使用或者测试的时候，我们需要创建的对象是`AnnotationConfigApplicationContext` 而非`ClassPathXmlApplicationContext`，其他的使用上面两者没有任何区别

        ctx = new AnnotationConfigApplicationContext(AppConfigAnnotation.class);

