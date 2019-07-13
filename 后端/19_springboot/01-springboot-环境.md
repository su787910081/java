


> ## 在线搭建springboot 工程

- 访问地址 : http://start.spring.io



--- 

> ## Springboot 手动搭建maven 工程

- 使用`maven-archetype-quickstart` 骨架完成java 工程 spring boot 创建;
    > - `maven-archetype-quickstart` 可以理解为一个标准的java 工程，由maven 来管理; 

- 添加pom 文件中的配置
    > 1. 将maven 工程转化成springboot框架，需要继承maven 父级依赖: 
    >> <details>
    >> <summary><mark><font color=darkred>maven 父级依赖配置</font></mark></summary>
    >> 
	>>      <parent>
	>>      	<groupId>org.springframework.boot</groupId>
	>>      	<artifactId>spring-boot-starter-parent</artifactId>
	>>      	<version>1.5.4.RELEASE</version>
	>>      	<relativePath /> <!-- lookup parent from repository -->
	>>      </parent>
	>>      <properties>
	>>      	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	>>      	<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	>>      	<java.version>1.8</java.version>
	>>      </properties>
    >> </details>
    > 2. 希望当前工程是满足web应用,基于springmvc spring框架的工程.需要在添加一个组件
    >> <details>
    >> <summary><mark><font color=darkred>web 应用依赖组件</font></mark></summary>
    >> 
	>>   	<dependency>
	>>   		<groupId>org.springframework.boot</groupId>
	>>   		<artifactId>spring-boot-starter-web</artifactId>
	>>   	</dependency>
    >> </details>
    > 3. 添加一个springboot 插件，用于maven install
    >> <details>
    >> <summary><mark><font color=darkred>build 标签</font></mark></summary>
    >> 
	>>      <build>
	>>      	<finalName>springboot1803</finalName>
	>>      	<plugins>
	>>      		<plugin>
	>>      			<groupId>org.springframework.boot</groupId>
	>>      			<artifactId>spring-boot-maven-plugin</artifactId>
	>>      		</plugin>
	>>      		<plugin>
	>>      			<artifactId>maven-compiler-plugin</artifactId>
	>>      			<configuration>
	>>      				<source>1.8</source>
	>>      				<target>1.8</target>
	>>      			</configuration>
	>>      		</plugin>
	>>      	</plugins>
	>>      </build>
    >> </details>

> ## 编写一个springboot 启动类

- Starter 类的实现
    > - Starter 类的实现
    >> <details>
    >> <summary><mark><font color=darkred>实现代码</font></mark></summary>
    >> 
    >>      import org.springframework.boot.SpringApplication;
    >>      import org.springframework.boot.autoconfigure.SpringBootApplication;
    >>      
    >>      // springboot 框架的核心注解
    >>      @SpringBootApplication
    >>      @Controller
    >>      public class Starter {
    >>          // springboot 工程 的入口方法，与标准java 入口方法一致
    >>          public static void main(String[] args) {
    >>              // 调用spring 应用的run 方法，将根据自动配置，默认配置，完成初始化的创建，根据依赖完成 所有功能的自动 配置
    >>              SpringApplication.run(Starter.class, args);
    >>          }
    >>      
    >>          @RequestMapping("hello")
    >>          @ResponseBody
    >>          public String hello() {
    >>              return "hello spring boot, hello 1803";
    >>          }
    >>      }
    >> </details>
    > - `@SpringBootApplication` 注解
    >> - 它是一个组合注解,例如service 就是组合了component 注解完成bean 的注入功能；
    >> - 包含自动配置，包含依赖配置，包含自动扫描；
    >> 1. `@SpringBootConfiguration`:读取springboot的配置文件application.properties,对于没有设定的内容默认配置(习惯优先于配置)
    >> 2. `@EnableAutoConfiguration`: 根据依赖的jar包将springboot工程需要的其他内容进行自动配置,例如依赖了starterweb,springboot会认为你要开发一个web应用,web.xml;
    >> 3. `@ComponentScan`: 启动类所在的包路径的同级包和下级包中所有spring需要加载,启动的注解一旦存在,将会自动在spring容器启动时,加载到内存中,等待注入,等待使用.

> ## 发布springboot 工程
- 如何发布
    > - `右键项目  --> run as  --> maven install`
    > - 这个时候在`Navigator` 视图下的`target` 目录下面会有一个`.jar` 的文件。它就是发布的工程文件。
    > - 我们可以直接使用命令来运行它。
    >> `java -jar **.jar`

---

> ## springboot 引入第三方项目的配置
- 引入第三方项目(或者与启动类不在同一级包以及下一级包的其他包的类)，它使用spring 框架。这个时候我们需要添加对第三方的spring 的配置，并引入到自己的工程中。 
    > - 首先我们有一个非启动类能扫描 到的包下的一个类
    >> <details>
    >> <summary><mark><font color=darkred>class HelloService</font></mark></summary>
    >> 
    >>      @Service
    >>      public class HelloService {
    >>      
    >>          public String sayHello() {
    >>              return "hello Service";
    >>          }
    >>      }
    >> </details>
    > - 第二，为这个类添加spring bean 配置
    >> <details>
    >> <summary><mark><font color=darkred>applicationContext.xml</font></mark></summary>
    >> 
    >>      <?xml version="1.0" encoding="UTF-8"?>
    >>      <beans default-lazy-init="true"
    >>          xmlns="http://www.springframework.org/schema/beans" xmlns:p="http://www.springframework.org/schema/p"
    >>          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
    >>          xmlns:tx="http://www.springframework.org/schema/tx" xmlns:aop="http://www.springframework.org/schema/aop"
    >>          xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:util="http://www.springframework.org/schema/util"
    >>          xmlns:jpa="http://www.springframework.org/schema/data/jpa"
    >>          xsi:schemaLocation="  
    >>                  http://www.springframework.org/schema/beans   
    >>                  http://www.springframework.org/schema/beans/spring-beans-4.3.xsd  
    >>                  http://www.springframework.org/schema/mvc   
    >>                  http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd   
    >>                  http://www.springframework.org/schema/tx   
    >>                  http://www.springframework.org/schema/tx/spring-tx-4.3.xsd   
    >>                  http://www.springframework.org/schema/aop 
    >>                  http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
    >>                  http://www.springframework.org/schema/util 
    >>                  http://www.springframework.org/schema/util/spring-util-4.3.xsd
    >>                  http://www.springframework.org/schema/data/jpa 
    >>                  http://www.springframework.org/schema/data/jpa/spring-jpa-1.3.xsd
    >>                  http://www.springframework.org/schema/context
    >>                  http://www.springframework.org/schema/context/spring-context-4.3.xsd">
    >>          <bean class="cn.tedu.HelloService" />
    >>      </beans>
    >> </details>
    > - 第三，因为第三方项目的包不是在我们的入口 类的同级包或者下一级包中。
    > - 所以我们需要一个可以被扫描到的类(与启动类同级或者下级包路径)。
    > - 然后给这个类添加两个注解(`@ImportResource("classpath:/applicationContext.xml")` 和 `@Configuration`) 以使得我们能识别相应的spring 配置文件。
    > - 只要按上述的注解添加了一个类之后就可以识别第三方项目中的对象了。
    > - 其实这个类什么都没做，是一个空类，只是添加了两个注解而以。它仅仅是起到一个连接的作用。
    >> <details>
    >> <summary><mark><font color=darkred>class Configuration</font></mark></summary>
    >> 
    >>      import org.springframework.context.annotation.ImportResource;
    >>      
    >>      @ImportResource("classpath:/applicationContext.xml")
    >>      @org.springframework.context.annotation.Configuration
    >>      public class Configuration {
    >>      }
    >> </details>
    > - 最后，在我们的springboot 工程中使用它
    >> <details>
    >> <summary><mark><font color=darkred>class Controller</font></mark></summary>
    >> 
    >>      @Controller
    >>      public class Demo01Controller {
    >>      
    >>          @Autowired
    >>          private HelloService service;
    >>          
    >>          @RequestMapping("hello")
    >>          @ResponseBody
    >>          public String hello() {
    >>              return service.sayHello();
    >>          }
    >>      }
    >> </details>

- 注解`@RestController`
    > - 它是一个类注解，也是一个组合注解(`@ResponseBody` + `@Controller`)。
    > - 如果类上面添加了这个注解，那么我们就不需要在每个方法上面添加注解`@ResponseBody` 来表示返回的是一个结果集了。




---

> ## springboot 修改访问端口

- 在全局配置文件`application.properties`中
    > - server.port=8091
    > - server.context-path=/


















