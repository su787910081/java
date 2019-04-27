
> ## springboot 与持久层mybatis

- pom 依赖

    > - springboot 的父级依赖以及WEB 应用的依赖组件(<mark>查看01-springboot-环境.md</mark>)
    > - 还需要另外两个组件依赖
    >> <details>
    >> <summary><mark><font color=darkred>mybatis 组件依赖</font></mark></summary>
    >> 
	>>  	<dependency>
	>>  		<groupId>org.mybatis.spring.boot</groupId>
	>>  		<artifactId>mybatis-spring-boot-starter</artifactId>
	>>  		<version>1.1.1</version>
	>>  	</dependency>
    >> </details>
    >> <details>
    >> <summary><mark><font color=darkred>mysql 组件依赖</font></mark></summary>
    >> 
	>>	    <!--添加mysql的组件-->
    >>        <dependency>
    >>            <groupId>mysql</groupId>
    >>            <artifactId>mysql-connector-java</artifactId>
    >>        </dependency>
    >> </details>

- application 全局配置文件
    > - 不需要spring 整合mybatis 配置xml 文件，mybatis 配置xml 文件。
    > - 所有的相关配置，都在全局配置文件(`application.properties`)中，数据库相关配置已经整理在jpa 中。
    >> <details>
    >> <summary><mark><font color=darkred>mysql 配置</font></mark></summary>
    >> 
    >>      # mysql 配置
    >>      spring.datasource.driver-class=com.mysql.jdbc.Driver
    >>      spring.datasource.url=jdbc:mysql://localhost:3306/springboot
    >>      spring.datasource.username=root
    >>      spring.datasource.password=root
    >> </details>
    >> <details>
    >> <summary><mark><font color=darkred>mybatis 配置</font></mark></summary>
    >> 
    >>      # mybatis
    >>      mybatis.typeAliasesPackage=com.jt.pojo
    >>      mybatis.mapperLocations=classpath:mappers/*.xml
    >>      # 属性与字段的驼峰映射，一但定义这个映射关系，
    >>      # 需要在编辑pojo 类时严格遵守，
    >>      # 否则你还需要类似于JPA@Column 注解帮你完成对应
    >>      # 例如: 属性(userName)  <-->  字段(user_name)
    >>      mybatis.configuration.mapUnderscoreToCamelCase=true
    >>      # 关闭自带缓存  redis 替代
    >>      mybatis.configuration.cacheEnabled=false
    >> </details>


- 扫描mapper接口类, 创建实例
    > - 我们需要将我们创建的接口类由mybatis 来创建实例，那么我们需要让springboot 认识到它们。
    >> <details>
    >> <summary><mark><font color=darkred>使用注解@MapperScan 添加扫描</font></mark></summary>
    >> 
    >>      @MapperScan("com.jt.mapper")
    >>      public class Starter {
    >>      }
    >> </details>



















