> ## springboot 整合JPA

- > JPA 的技术api, 原理 与hibernate 类似，对于复杂的增删改查的SQL 语句，无法灵活使用，转向mybatis 持久层，利用xml 映射文件，mapper 接口类完成关联，外键等操作。


- jpa 的pom 依赖
    > - 添加jpa 组件依赖
    >> <details>
    >> <summary><mark><font color=darkred>jpa 组件</font></mark></summary>
    >> 
	>>  	<!--添加jpa的组件 -->
	>>  	<dependency>
	>>  		<groupId>org.springframework.boot</groupId>
	>>  		<artifactId>spring-boot-starter-data-jpa</artifactId>
	>>  	</dependency>
    >> </details>
    > - 添加mysql 组件依赖
    >> <details>
    >> <summary><mark><font color=darkred>mysql 组件</font></mark></summary>
    >> 
	>>	    <!--添加mysql的组件-->
    >>        <dependency>
    >>            <groupId>mysql</groupId>
    >>            <artifactId>mysql-connector-java</artifactId>
    >>        </dependency>
    >> </details>

- 添加一个全局配置文件
    > - 在`src/main/resources` 目录下面创建一个名为`application.properties` 的配置文件
    > - 这个文件我们似乎没有在任何一个地方添加配置，所以这个名字估计只能这样写。而且springboot 自己会找到它，并读取它。
    >> <details>
    >> <summary><mark><font color=darkred>application.properties</font></mark></summary>
    >> 
    >>      # 配置端口号
    >>      server.port=8089
    >>      server.context-path=/
    >> 
    >>      # mysql 配置
    >>      spring.datasource.driver-class=com.mysql.jdbc.Driver
    >>      spring.datasource.url=jdbc:mysql://localhost:3306/springboot
    >>      spring.datasource.username=root
    >>      spring.datasource.password=password
    >>
    >>      # JAP 配置
    >>
    >>      # JPAjpa持久层继承
    >>      # hibernate框架;每次连接操作数据库时,默认drop,create,
    >>      # 这个key的value值update表示连接到数据库不在重新创
    >>      # 建只是进行数据的更新(增删改查不会重建数据库)
    >>      spring.jpa.ddl-auto=update
    >>      # JPA 的hibernate 底层翻译SQL 语句打印
    >>      spring.jpa.show-sql=true
    >> </details>

- pojo 类 与数据库表的映射关系
    > - 首先，我们需要在数据库中添加一个相关的数据库表
    > - 其次，添加一个与该表对应的pojo 类
    > - 再次，我们不需要再像mybatis 一样添加mapper.xml 配置文件就可以直接将这个类与数据库表相匹配。
    >> - 这需要用到一个类注解`@Entity`
    >> - 如果表名与类名不一致，那么我们需要用到另一个类注解`@Table(name="tb_user")`
    > - 这个类中的字段与表中的字段相对应，如果不对应我们可以使用注解的方式使得属性与字段相对应。
    >> - 属性注解`@Column(name="name")`
    > - 如果某个字段是自增字段，那么我们还有一个属性注解
    >> - 属性注解`@GeneratedValue(strategy=GenerationType.IDENTITY)`
    > 代码示例:
    >> <details>
    >> <summary><mark><font color=darkred>User 映射类</font></mark></summary>
    >> 
    >>      import javax.persistence.Column;
    >>      import javax.persistence.Entity;
    >>      import javax.persistence.Id;
    >>      import javax.persistence.Table;
    >>
    >>      @Entity
    >>      @Table(name="tb_user")
    >>      public class User {
    >>          @Id
    >>          @GeneratedValue(strategy=GenerationType.IDENTITY)    // 自增
    >>          private Integer id;
    >>          @Column(name="name")    // 对应表中的字段名称
    >>          private String name;
    >>      
    >>          private Integer age;
    >> 
    >>          ...  get set
    >>      }
    >> </details>
    > - 这个User 类我们需要让它被我们使用，还需要添加一个接口，并继承一个泛型。同时将User 作为泛型的参数。
    >> <details>
    >> <summary><mark><font color=darkred>UserDao 接口</font></mark></summary>
    >> 
    >>      import org.springframework.data.jpa.repository.JpaRepository;
    >>      
    >>      import com.jt.pojo.User;
    >>      
    >>      // JPA 的API 中有一个repository 持久层接口，在继承时利用jar 包依赖
    >>      // 两个泛型，
    >>      // 第一个泛型定义 的是当前dao 接口对应的pojo 类；
    >>      // 第二个泛型表示当前pojo 使用的序列化ID 的类型
    >>      public interface UserDao extends JpaRepository<User, Long> {
    >>      }
    >> </details>
    > - 然后我们就可以使用这个dao 接口操作数据库了
    >> <details>
    >> <summary><mark><font color=darkred>使用UserDao 接口操作数据</font></mark></summary>
    >> 
    >>      @Autowired
    >>      private UserDao userDao;
    >>      
    >>      @RequestMapping("findAll")
    >>      public List<User> findAll() {
    >>          List<User> list = userDao.findAll();
    >>          return list;
    >>      }
    >> </details>

- 数据校验
    > - 校验注解
    >> - `@NotEmpty(message="你没有身份证")`
    >> - `@NotEmpty(message="你没有出生证")`
    >> - `@Min(value=1,message="你还没有出生")`
    >> - `@Max(value=256,message="你取了多少个媳妇了")`
    >> <details>
    >> <summary><mark><font color=darkred>校验注解</font></mark></summary>
    >> 
    >>      @RequestMapping("addUserJpa")
    >>      // 如果参数传递的值是id=** name=** age=**
    >>      public String addJpaUser(@Validated User user, BindingResult bindingResult) {
    >>          // jpa测试传参写数据库校验
    >>          // bindingResult是在校验失败是接收信息的对象 message是由他管理的
    >>          // 由失败信息,空,大于最大,小于最小
    >>          // JPA扩展注解(与传参有关)
    >>          // 分区 day01 的第 33 页//由失败信息,空,大于最大,小于最小
    >>          // 参数有错误,校验不通过
    >>      
    >>          if (bindingResult.hasErrors()) {
    >>              return "新增失败" + bindingResult.getFieldError().getDefaultMessage();
    >>          }
    >>          
    >>          userDao.save(user);
    >>          return "新增成功";
    >>      }
    >> </details>