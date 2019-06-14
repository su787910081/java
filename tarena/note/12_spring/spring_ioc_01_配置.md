# spring 是什么
- 它是一个框架(IOC, AOP, ...)，整合一些资源的框架。提供完整的解决方案。
- spring 容器工厂初始化及bean 对象的创建以及获取.<br>
	![Alt Text](./img/SpringFrame.jpg "SpringFrame")

## Spring 依赖jar 包
- commons-logging-1.2.jar
- spring-aop-4.3.9.RELEASE.jar
- spring-beans-4.3.9.RELEASE.jar
- spring-context-4.3.9.RELEASE.jar
- spring-core-4.3.9.RELEASE.jar
- spring-expression-4.3.9.RELEASE.jar

## Spring IOC
-  Spring IOC 是实现了控制反转功能的一个容器对象，它通过这个容器对象实现对象之间依赖关系的管理，目的是实现对象之间解耦合。以提高程序的可维护及可扩展性。
-  Spring 中的IOC 功能如何实现?(借助DI：依赖注入)


## spring Bean容器
- Spring Bean 容器负责创建(底层根据元数据的描述与反射技术进行对象的创建)Bean 对象，并管理Bean 对象。
- Spring 中提供了一个工厂，这个工厂的作用是根据配置信息创建对象。
- Spring 中提供了一个容器，这个容器用于存储对象，以及管理对象之间的依赖关系

## spring bean对象
- 配置文件<br>

		<?xml version="1.0" encoding="UTF-8"?>
		<beans 
		    default-lazy-init="true"
		    xmlns="http://www.springframework.org/schema/beans" 
		    xmlns:p="http://www.springframework.org/schema/p"
		    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		    xmlns:context="http://www.springframework.org/schema/context"
		    xmlns:tx="http://www.springframework.org/schema/tx"
		    xmlns:aop="http://www.springframework.org/schema/aop" 
		    xmlns:mvc="http://www.springframework.org/schema/mvc"
		    xmlns:util="http://www.springframework.org/schema/util"
		    xmlns:jpa="http://www.springframework.org/schema/data/jpa"
		    xsi:schemaLocation="  
		       http://www.springframework.org/schema/beans   
		       http://www.springframework.org/schema/beans/spring-beans-4.3.xsd  
		       http://www.springframework.org/schema/mvc   
		       http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd   
		       http://www.springframework.org/schema/tx   
		       http://www.springframework.org/schema/tx/spring-tx-4.3.xsd   
		       http://www.springframework.org/schema/aop 
		       http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
		       http://www.springframework.org/schema/util 
		       http://www.springframework.org/schema/util/spring-util-4.3.xsd
		       http://www.springframework.org/schema/data/jpa 
		       http://www.springframework.org/schema/data/jpa/spring-jpa-1.3.xsd
		       http://www.springframework.org/schema/context
		       http://www.springframework.org/schema/context/spring-context-4.3.xsd">
			<bean id="date1" class="java.util.Date" />
		</beans>
- bean 对象的使用<br>
	1. 构造容器对象<br>

			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	2. 获取我们需要的对象<br>
		> &emsp;默认情况下我们必须要有无参构造函数，如下示例<br>
		> &emsp;xml 配置文件中配置的Date bean 对象<br>
		>> `<bean id="date1" class="java.util.Date" />`

		> 直接获取对象，然后强转得到对象<br>
		>> `Date date1 = (Date)ctx.getBeans("date1");`

		> 直接指定类型，不使用强转<br>
		>>`Date date2 = ctx.getBean("date1", Date.class);`
	3. 释放资源<br>
		> `ctx.close();`
- 抽象类对象的创建<br>
	> ![Alt Text](./img/absctrct.jpg)<br>


## Spring 依赖注入，bean 对象的配置
1. 直接通过类的构造方法
	> `<bean id="date1" class="java.util.Date" />`
2. 类的静态方法
	> `<bean id="c2" class="java.util.Calendar" factory-method="getInstance" />`
3. 通过类的实例方法
	> 此方法依赖已存在的bean 对象"c2"
	>> `<bean id="date2" factory-bean="c2" factory-method="getTime" />`
4. 使用带参构造函数构造bean 对象
	- 按昭顺序给构造函数传入实参

			<bean id="sdf" class="java.text.SimpleDateFormat">
				<constructor-arg value='yyyy/MM/dd' />
			</bean>

	- 多个参数的构造函数(*<span style="color: red">构造注入</span>*)
		- 我们可以使用`index="0"` 来指定其实参的位置，**index 的值从0开始**。
			> *一般情况下我们不会用index 来处理参数的位置。正常我们会按参数的定义的顺序来填写*
		- `value` 属性后面的值必须用双引号引起来，即使是整数也需要。
		- 在`constructor` 标签中我们可以为属性指定其数据类型 **<span style="color:red">`type="int"`</span>**
		
				<bean id="dataSource2" class="beans.DataSource">
					<constructor-arg index="1" value="jdbc:mysql:///test" />
					<constructor-arg index="0" value="com.mysql.jdbc.Driver" />
					<constructor-arg value="root" />
					<constructor-arg value="123456" type="java.lang.Integer"/>
					<constructor-arg value="1000" type="int"/>
				</bean>
5. **<span style="color:red; background-color:#DDA0DD">单例、多例属性:</span>** 默认情况下，多次获取一个bean 对象都是同一个bean 对象。如果要每次都是不同的对象，则需要用到scope 属性
	> &emsp;`<bean id="helloService" class="beans.HelloService" scope="prototype" />`<br>
	> &emsp;如果是单例对象时，需要注意单例对象的线程安全问题，主要是对于类里面的一些实现。<br>
6. 给bean 对象设置初始化与销毁方法
	- 初始化与销毁bean 对象是，如果对象非singleton 时。其作用域不由spring 控制。所以一般我们给singleton 属性的对象设置。
	- 当 `scope = prototype` 时销毁方法将不会被调用，而初始化方法是可以被调用的。
		> 问题是：既然多例的不由spring 来管理，那你把它给spring 做什么呢？所以一般情况下我们都不用多例这种模式。

		`<bean id="helloService" class="beans.HelloService" scope="singleton" init-method="init" destroy-method="doDestroy" />`
7. 依赖注入
	- set 注入: 为指定bean 对象的属性配置相应的值，使用`property` 标签来指定，但是这个需要此类<span style="color:red">**必须对这个属性添加对应的set 方法**</span>
	- **所谓的set 注入也就是调用类对象的set 方法来对其属性进行赋值**

			<bean id="dataSource" class="beans.DataSource">
				<!-- 为此对象的driver 属性赋值，但是前提是这个类必须要有这个类的对象的set 方法 -->
				<property name="driver" value="com.mysql.jdbc.Driver" />
				<property name="url" value="jdbc:mysql:///test" />
				<property name="username" value="root" />
				<property name="password" value="123456" />
			</bean>
	- 构造注入，查看前面 *多个参数的构造函数(构造注入)*
	- **<span style="color: red">所谓的构造注入，实际就是给构造函数指定它所需要的实参，从而通过调用带参构造函数来构造此bean 实例</span>**
8. `ref` 属性，引入存在的bean 来作为一个方法的实参 <br>
	![Alt Text](./img/ref.png)
9. `autowire` 自动装配注入
	- **<span style="color:red">实际开发中，这种方法不常用，因为会有很多不确定性。</span>**
	- 默认值是`no` 不进行自动装配
	- `autowire=byName`	按名称装配
		- 由spring 获取bean 对象中的set 方法，取方法名set 单词后面的内容。
			> &emsp;bean 对象能成功创建<br>
			> &emsp;比如：类中有一个setDataSource 方法，则到spring 中去找`id="dataSource"` 对应的bean 对象，用此对象来注入。<br>
			> &emsp;但是如果类型不匹配，则有报错的危险。<br>
			>> ![Alt Text](./img/byNameAutowire.jpg)
		- 默认空参构造函数
		- 找此类的所有set 方法，然后在spring 容器中匹配是否有此bean 对象id 相匹配的字符串，如果有就注入，否则不注入。
	- `autowire=byType` 按类型装配
		- 按类型匹配时，其实际是按set 注入
		- 去查找set 方法中参数的类型，然后根据类型到spring 容器中找到此类型的bean 对象作为参数。如果有多个此类型的bean 对象，则会出错。
		- 当按类型匹配时，只允许此类型的bean 对象有且仅有一个，否则会报错。
		- <span style="color:red">`expected single matching bean but found 2: dataSource1,dataSource2`</span>
			![Alt Text](./img/byTypeAutowire.jpg)
	- `autowire=constructor` 构造装配
		- 与byType 类似，是按构造函数的类型来匹配bean 对象
		- 与byType 不同的是，如果找到多个匹配的bean 对象，则不执行装配动作。
		- 但是如果找到多个匹配的bean 对象，则会再次匹配id 值与参数值的匹配。如果匹配就会正确装配。

## Spring 中复杂对象(array, list, map, properties)的注入
- 数组与List 的注入
	- 需要有对应的set 方法

			// private String[] hobby;
			// private List<String> address;
			<bean id="c" class="ioc.ComplexObject">
				<property name="hobby">
					<list>
						<value>足球</value>
						<value>篮球</value>
					</list>
				</property>
				<property name="address">
					<list>
						<value>北京</value>
						<value>上海</value>
						<value>雄安</value>
					</list>
				</property>
			</bean>
- map 的注入(`name="phones"` 中的 `phones` 是bean 对象类中的一个成员属性)
	1. 普通类型数据
	
			<property name="phones">
				<map>
					<entry key="k1" value="1001" ></entry>
					<entry key="k2" value="1002" ></entry>
				</map>
			</property>
	2. 引用已存在的bean 对象，使用`value-ref`
	
			<property name="phones">
				<map>
					<entry key="k1" value-ref="bean_id_01" ></entry>
					<entry key="k2" value-ref="bean_id_01" ></entry>
				</map>
			</property>
- 引入properties 配置文件，从配置文件中取对应的值
	- `name="configs"` 中r configs 是bean 对象类中的一个成员属性
	- 要使用`util:properties` 需要引入`xmlns:util="http://www.springframework.org/schema/util"`否则不能使用
	- `<util:properties id="cfg" location="classpath:config.properties" />` 引入配置文件config.properties，同时指定`id="cfg"` 则`#{cfg.port}` 就指从config.properties 文件中取`key=port` 的值
	- 也可以直接在里面填写明文数据: `<prop key="cKey3">www.tedu.cn</prop>`

			<bean>
				<property name="configs">
					<props>
						<prop key="cKey1">#{cfg.port}</prop>
						<prop key="cKey2">#{cfg.host}</prop>
						<prop key="cKey3">www.tedu.cn</prop>
					</props>
				</property>
			</bean>
			<util:properties id="cfg" location="classpath:config.properties" />
- 引入其它spring bean 配置文件
	- `<import resource="ioc.xml"/>`



## Spring Bean 对象的作用域
- Spring 中Bean 对象的作用域一般使用scope 属性进行配置，最重要的有两个。
	- 默认情况下其值是: singleton(单例，每此获取都是同一个bean 对象)
		`<bean id="helloService" class="beans.HelloService" scope="singleton" />`
	- 如果指定其值为: prototype(多例，每次获取都创建一个新的bean 对象)，多例时，其生命周期不由spring 控制
		`<bean id="helloService" class="beans.HelloService" scope="prototype" />`

## Spring Bean 对象的延迟加载
- 在配置文件的`beans` 标签下面的第一行有一个`default-lazy-init="true"`可以指定其全部bean 对象都进行延迟加载。
- 需要在`bean`标签下面添加一个`lazy-init="true"` 属性，来指定此Bean 对象需要延迟加载。延迟加载是指定其在使用的时候才加载此对象。

### 何为元数据(重要)？
- 用来描述数据的数据，就好像一张表的表头
	<table>
		<tr><td>名字</td><td>婚否</td><td>性别</td></tr>
		<tr><td>suyh</td><td>false</td><td>man</td></tr>
		<tr><td>tina</td><td>true</td><td>women</td></tr>
	</table>
- Java 中常用的元数据表示形式？
	1. XML
	2. 注解




### FAQ
1. 何为Spring Bean 容器？
	- 用于创建Bean 对象，管理Bean 对象的那个容器，我们称为Spring Bean 容器。
	- `ClassPathXmlApplicationContext`
2. SpringBean 容器与SpringIOC 容器有什么不同吗?
	- SpringIOC 容器本质上指的就是SpringBean 容器;
	- SpringBean 容器中最核心的一个机制是IOC 机制(控制反转)，所以有时候又将SpringBean 容器称之为SpringIOC;
3. SpringIOC 如何理解？
	- IOC 是Spring 中提供的一种控制反转<span style="color:red">机制</span>，目的是将我们项目中的对象依赖管理交给Spring 实现，以实现对象关系的解耦，提高程序的可扩展性;
4. Spring DI(依赖注入) 如何理解？
	- DI 是Spring 中的依赖注入机制，IOC 的实现需要借助这种机制。我们通常会这样理解，SpringBean 容器中的IOC 思想一种目标，DI 是实现这种思想的目标的手段。
5. Spring 中配置bean 的方式有几种？
	> - 基于XML
	>> 优点: 代码侵入性小，每次修改只需要修改配置文件然后重启; <br />
	>> 缺点: 但是XML 方式的灵活性不太好 <br />
	> - 基于注解
	>> 优点：灵活性好；<br />
	>> 缺点：存在代码的侵入性(每次修改都需要添加代码处理)；<br />

	- 提示：
		> 基于注解方式虽然具备一定的代码侵入性，但是这种侵入性属于声明式侵入性。这种侵入性在程序中是允许的，它属于弱侵入性。<br />
6. Spring 中集合的注入的方式? (map, list, set)
7. Spring 中依赖注入表达式的应用? #{key.fileKey}
8. Spring 中修饰类的注解常用的有哪些？
	- `@Controoler` 一般用于描述控制层对象
	- `@Service` 一般用于描述业务层对象
	- `@Repository` 一般用于描述数据层对象(dao)
	- `@Component` 一般用于修饰其他组件

## Spring IOC 容器注解
- Spring 运行时会扫描此包，包括子包中的.class 文件。然后将有`@Component, @Controller, @Service, @Repository` 注解描述的类构建成对象，然后存储到map, key 默认为类名，类名的第一个字母小写。
	> - `@Controoler` 一般用于描述控制层对象
	> - `@Service` 一般用于描述业务层对象
	> - `@Repository` 一般用于描述数据层对象(dao)
	> - `@Component` 一般用于修饰其他组件
	>>	- `<context:component-scan base-package="project" />` 对应下面的注解
	>>>		package project.service;
	>>>		import org.springframework.stereotype.Component;
	>>>		
	>>>		@Component
	>>>		public class UserService {
	>>>		}
