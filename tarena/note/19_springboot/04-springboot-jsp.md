



> ## springboot 页面jsp 

> - 使用`maven-archetype-webapp` 骨架，创建一个WEB 工程
> - JSP 文件在内嵌的servlet 容器上运行有问题(java工程); 可以利用springboot 整合前端众多的js 框架; 
> - 使用eclipse 的web 工程可以做到jsp 文件的使用，单独维护了一个web 应用的目录结构。




- 默认首页无法显示出来，这个需要配置一下
- 添加相关依赖

    >>      <dependency>
	>>  		<groupId>org.springframework.boot</groupId>
	>>  		<artifactId>spring-boot-starter-web</artifactId>
	>>  	</dependency>
	>>  	<!--servlet依赖: 默认首页需要继承，使用api 包 -->
	>>  	<dependency>
	>>  		<groupId>javax.servlet</groupId>
	>>  		<artifactId>javax.servlet-api</artifactId>
	>>  	</dependency>
	>>  	<!--jstl依赖 -->
	>>  	<dependency>
	>>  		<groupId>javax.servlet</groupId>
	>>  		<artifactId>jstl</artifactId>
	>>  	</dependency>
	>>  	<!--使jsp页面生效 -->
	>>  	<dependency>
	>>  		<groupId>org.apache.tomcat.embed</groupId>
	>>  		<artifactId>tomcat-embed-jasper</artifactId>
	>>  	</dependency>
- 修改启动类代码，完成首页的内部 逻辑 跳转。
    > 重写父类的`addViewControllers` 方法
    >>       @SpringBootApplication
    >>       public class WebStarter extends WebMvcConfigurerAdapter {
    >>       
    >>           public static void main(String[] args) {
    >>               SpringApplication.run(WebStarter.class, args);
    >>           }
    >>           
    >>           @Override
    >>           public void addViewControllers(ViewControllerRegistry registry) {
    >>               // 调用内部 controller 接收'/' 路径，和自定义单独写controller 效果一样。
    >>               registry.addViewController("/").setViewName("forward:/index.jsp");
    >>               
    >>               // 权限控制，把当前这个controller 的接收默认的路径"/" 设置为最高级，别的相同的controller 无效
    >>               registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    >>       
    >>               super.addViewControllers(registry);
    >>           }
    >>       }




- aplication 中添加前缀后缀
    > - 添加配置
    >>      spring.mvc.view.prefix=/WEB-INF/view/
    >>      spring.mvc.view.suffix=.jsp

