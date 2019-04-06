

## 拦截器

![](./img/interceptor.jpg)

## 如何实现一个拦截器
- 实现接口`HandlerInterceptor`
    > 实现接口方法: `preHandle`

    > 方法1: `boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;`
    >> 此方法在`Controller` 业务方法执行之前执行<br>
	>> @return `false`: 表示拦截；`true`: 表示放行<br>


    > 方法2: `void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;`
    >> 此方法在`Controller` 业务方法执行之后执行<br>
    >> `handler` 指向具体的`Controller` 对象<br>
    >> `modelAndView` 如果以`Controller` 没有，那么该值将会是`null`<br>

    > 方法3: `void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;`
    >> 此方法在`Controller` 业务方法执行结束，并且视图解析OK之后执行



- 添加配置，在`spring_mvc.xml` 文件中进行配置，将自己实现的拦截器添加到配置文件中

        <!-- 配置拦截器 -->
        <mvc:interceptors>
            <mvc:interceptor>
                <!-- 指定拦截哪些请求路径 -->
                <mvc:mapping path="/**"/>
                <!-- 排除对指定路径的拦截，这个需要在拦截(mvc:mapping)之后, ".do" 不能省 -->
                <mvc:exclude-mapping path="/sys/doLogin.do"/>
                <!-- 拦截器 -->
                <bean class="cn.spring.interceptor.SysUserInterceptor"></bean>
            </mvc:interceptor>
        </mvc:interceptors>


## 多个拦截器
> 当我们系统中有多个拦截器时，这些拦截器可以构成一个拦截器链<br>
> 只有当所有的拦截器都放行了，才会执行`Controller`<br>
> 拦截器的顺序按配置先后执行<br>
