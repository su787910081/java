

# 切入点表达式: `bean`、`within`、execution
## bean 表达式
- `@Before("bean(userServiceImpl)")`
    > bean 表达式中<span style="color:red">不允许指定方法</span>。bean 表达式中只能是bean 对象的ID；<br>
- `@After("bean(*ServiceImpl)")`
- `@Before("bean(*Service*)")`

## within 表达式
> within 表达式是针对包级别的，它可以指定到某一个级别的包。然后包含这个包及其子包中的业务类。<br>

- 指定一个具体的类
    > `within(com.project.service.impl.UserServiceImpl)` <br>
    > 在`UserServiceImpl` 这个业务类中的方法被调用前，下面的这个方法将会先调用<br>
    
        @Before("within(com.project.service.impl.UserServiceImpl)")
        public void beforeAdvice() {
            System.out.println("beforeAdvice()");
        }

- 指定一个包中所有的业务类
    > `within(com.project.service.*)`<br>

        @Before("within(com.project.service.*)")
        public void beforeAdvice() {
            System.out.println("TxManagerAspect.beforeAdvice()");
        }

- 指定一个包及其子包中所有的业务类
    > `within(com.project.service..*)`

        @Before("within(com.project.service..*)")
        public void beforeAdvice() {
            System.out.println("TxManagerAspect.beforeAdvice()");
        }

## execution 表达式
> 针对方法<br>
- 指定任意返回类型，某个包下，任意方法，任意参数
    > `execution(* com.project.service..*.*(..))`
- 指定参数类型
    > `execution(* com.project.service..*.*(String, int))`
- 指定第一个参数类型
    > `execution(* com.project.service..*.*(String, ..))`


---
## spring aop `Aspect` 中各个注解的一个执行顺序
    > 下面的 `try ... catch ... finally ... ` 可以帮助我们理解其执行的位置。<br>

        method() {
            try {
                @Before
                核心业务
                @AfterReturn    // 如果核心业务出问题，则此切点将不会执行
            } catch(Exception e) {
                @AfterThrowing
            } finally {
                @After
            }
        }

    1. 首先执行`@Before` 注解的方法，这个方法肯定会被执行; 
    2. 执行核心业务; 
    3. 执行`@After` 注解的方法；
    4. 执行`@AfterThrowing`  或者 `@AfterReturn`，它们两个是互斥关系。只有一个将会被执行。

## `@Before` 前置通知
> 在核心业务调用之前将被执行

## `@After` 后置(最终)通知
> 在核心业务全部调用完成之后将被执行。<br>
> 它不会被异常所影响<br>
> 但是它会在`@AfterThrowing` 之前被执行，这点与`try ... catch ... finally ...` 块不同。<br>

## `@AfterReturn` 业务正常结束
> 在核心业务return 语句正确调用之后执行<br>
> 如果核心业务出现了异常，则`@AfterReturn` 将不会被执行。但是`@After` 总是会被执行<br>
> 它与`@AfterThrowing` 相互斥，最多只出现其中的一个<br>

## `@AfterThrowing` 异常通知
> 在核心业务出现异常时，将被调用<br>
> 它的调用顺序将是在`@After` 之后。<br>

## `Around`环绕通知
> &emsp;一般环绕通知会独自使用，不与其它四个通知一起使用。<br>
> &emsp;可以改变目标方法的返回值。<br>
> &emsp;`joinPoint.proceed()` 如果没被调用，那么业务方法将不会被执行。<br>

- 环绕通知使用说明: 
    > &emsp;环绕一般不跟其他通知一起使用，所以可以使用注解`@Order(1)`来提升它的优先级<br>
    > &emsp;环绕通知方法的返回值类型一般为`Object`。<br>
    > &emsp;环绕通知方法中的参数为`ProceedingJoinPoint` 类型，是一个特殊的连接点类型，可以通过此类型的对象直接执行目标对象相关方法(`joinPoint.proceed()`)。<br>
    >> &emsp; <span style="color:red">这里必须要通过`joinPoint.proceed()`来调用目标方法，否则目标方法将不会被执行。 </span>
- 应用场景: 
    > &emsp; 目标方法执行前后都要添加扩展时，例如：事务处理(开启事务，提交事务，回滚事务，释放资源)。<br>






---
## `JoinPoint` 切入点
> 扩展业务具体执行的核心业务的具体方法<br>
> 通过这个`JoinPoint` 对象我们可以拿到核心业务的具体方法签名(包含方法名，参数类型等信息)<br>
> `JoinPoint` 对象在通知参数上
   
    @Before("execution(* com.project.service..*.*(..))")
	public void checkPermission(JoinPoint joinpoint) { ... }

- 通过`JoinPoint` 获取到具体方法签名

        Signature s = joinpoint.getSignature();
        String methodName = s.getName();
- 通过`JoinPoint` 获取到方法的参数列表

        Object[] args = joinpoint.getArgs();
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			Object a = args[i];
			System.out.println("a = " + a);
			System.out.println("a.class: " + a.getClass());
			params[i] = a.getClass();
		}
- 通过`JoinPoint` 获取到目标对象(注意，这里并非代理对象)

        Class<?> targetCls = joinpoint.getTarget().getClass();
    > 通过这个目标对象，我们可以得到具体的方法对象

        Method m = targetCls.getDeclaredMethod(methodName, params);

    > 有了这个方法对象，我们可以判断这个方法上面是否有我们需要的注解

        if (m.isAnnotationPresent(Permission.class)) { ... }

---
## 多切面执行顺序问题
- 基于XML 方式配置切面执行顺序时，在标签`<aop:aspect order="1">` 里面可以借助`order="1"`进行配置。

- 基于注解
    > 我们需要借助`@Order(1)` 对类进行注解，描述横切关注点(就是那个横切面)<br>
    >> 这里面序号越小的优先级越高<br>

        @Order(1)
        @Aspect
        @Component
        public class TxManager { ... }

