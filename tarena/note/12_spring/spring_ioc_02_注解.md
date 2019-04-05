# Spring 注解应用-系统组件

## Spring 中修饰类的注解常用的有哪些？
- `@Controller` 一般用于描述控制层对象(**控制层组件应用注解**)
- `@Service` 一般用于描述业务层对象(**业务层组件应用注解**)
- `@Repository` 一般用于描述数据层对象(**持久层组件应用注解\<dao\>**)
- `@Component` 一般用于修饰其他组件(**通用注解**)
> 这四种注解没有语法规则的特定使用地方，它们的使用地方都是一用行为约束。大家都这样默认处理。<br>
> 这四个注解只要添加在了一个类的上面，就是告诉spring 我这个类交给你来管理了。<br>
> 当然了除了有这个注解，我们还需要在配置文件中添加相对应包的配置标签。<br>
> 最后在使用的时候需要构造`ClassPathXmlApplicationContext` 对象，通过此对象我们来取bean 对象。<br>

## 使用注解的配置
- 我们在使用注解的时候也是需要添加配置文件的，不过注解的配置相对简单。
    > 我们只需要在配置文件中添加一行如下的配置就可以了。
    >> 它表示导入指定包`cn.tedu.project` 以及其子包下的所有注解的spring bean 对象

        <context:component-scan base-package="cn.tedu.project"></context:component-scan>

## 系统组件
- 注解Spring 的命名
    > `@Component`: 默认id 为类名首字母小写 <br />

        @Component
        public class Idgenerator {
            public IdGendenerator() { }
        }
    > `@Component("idg")`: 如果要给这个注解的bean 自定义一个名字，则直接在后面添加一个字符串就可以了。 <br />
    > 使用时: `ctx.getBean("idGenerator");` <br />

        @Component("idg")
        public class Idgenerator {
            public IdGendenerator() { }
        }
    > 使用

        public static void main(String[] args) {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

            Idgenerator id = ctx.getBean("idgenerator", Idgenerator.class);

            ctx.close();
        }

- 默认情况下注解Bean 对象是单例的`@Scope("singleton")`
    > 默认单例：`@Scope("singleton")` 单例时需要注意线程安全问题 <br />

        @Component("idg")
        @Scope("singleton")
        public class Idgenerator {
            public IdGendenerator() { }
        }
    > 可以多例：`@Scope("prototype")` 但是多例时需要自己管理对象的生命周期 <br />

        @Component("idg")
        @Scope("prototype")
        public class Idgenerator {
            public IdGendenerator() { }
        }
- 延迟加载机制
    > `@Lazy(value=true)`

        @Component("idg")
        @Lazy(value=true)
        public class Idgenerator {
            public IdGendenerator() { }
        }
- 构造完进行初始化与销毁操作
    > `@PostConstruct` 初始化，在对象构建之后执行 <br />
    > `@PreDestroy` 销毁，在对象释放之前执行 <br />

        @PostConstruct
        public void init() { }

        @PreDestroy
        public void destroy() { }

## 组件依赖关系
- 通过`@Autowired` 注解对存在依赖关系的Bean 对象进行注入
- <span style="color:red">`@Autowired` 配合 `@qualifier` 一般应用在属性或者构造方法上</span>
    - `MessageDaoImpl` 是一个spring bean 对象
    - `MessageServiceImpl` 也是一个bean 对象，但它还有一个成员`private MessageDao msgDao;` 的值需要依赖spring bean 进行注入
        > class MessageDaoImpl

            @Repository
            public class MessageDaoImpl implements MessageDao {
            }
        > class MessageServiceImpl

            @Service
            public class MessageServiceImpl implements MessageService {
                private MessageDao msgDao;
            }
    1. 带参构造函数
        > spring 会查看有`@Autowired` 注解的带参构造函数，然后去容器中找与参数相对应类型的bean 对象，用此对象作为参数传入，通过此方法对其注入。<br>
        > **同样需要注意的是：如果相同类型的bean 对象有多个，则会失败。**
        >> 解决这个问题，可以利用`@qualifier`注解，指定一个名字<br>
        >> <span style="color:yellow">如果只有一个带参构造函数的话，这个`@Autowired` 不写也是可以的</span><br>

            @Autowired
            public MessageServiceImpl(MessageDao msgDao) {
                this.msgDao = msgDao;
            }
        > 构造函数注解时，还可以直接写在构造函数的参数上面<br>
        > <span style="color: red; background-color:#DDA0DD">用得比较多的方法之一: </span>有的公司会要求这样做。<br>

            @Autowired
            public MessageServiceImpl(@Qualifier("messageDaoImpl") MessageDao msgDao) {
                this.msgDao = msgDao;
            }
        > **<span style="color:red">错误的情况: 无参构造函数是不会给赋值的</span>**
        >> 这个会导致msgDao 为空指针<br>
        >> 

            @Autowired
            public MessageServiceImpl() {
                System.out.println("MessageServiceImpl.MessageServiceImpl()");
            }
    2. 直接在变量声明的上方添加`@Autowired` 注解
        > spring 会通过此注解去容器中查找类型匹配的bean 对象，利用反射机制为其注入值。<br>
        > **同样需要注意的是：如果相同类型的bean 对象有多个，则会失败。**
        >> 解决这个问题，可以利用`@qualifier`注解，指定一个名字<br>
        >> <span style="background-color:#00F">`@Qualifier` 注解需要依赖`@AutoWire` 注解，它不允许单独存在</span> <br>
        >> <span style="color: red; background-color:#DDA0DD">用得比较多的方法之一</span><br>
        >>> 如果有对应此变量的带参构造方法，那么spring 它会首先利用构造方法处理。当没有对应的带参构造时才会轮到下面的方式来进行注入。<br>

            @Qualifier("messageDaoImpl")
            @Autowired
            private MessageDao msgDao;

    - **小结：**
        > `@Autowired` 注解的原理: 
        >> 1. spring 首先会去找是否有与此属性类型对应的带参构造函数，如果有那么它就通过此构造函数来构造注入。这个时候他只管调用此函数，如果此函数内部没有实现`this.msgDao = msgDao` 的话，那spring 是不负责的。<br>
        >> 2. 没有相应的带参构造函数的话，spring 会通过反射机制给它注入值。<br>

- 通过`@Resource` 注解对存在依赖关系的Bean 对象进行注入
    > &emsp;使用`@Resource` 注解<br>
    > &emsp;<span style="color:red">一般用在属性或set 方法上，用于为对象参数赋值</span><br>
    > &emsp;spring 发现属性上有此注解时，先查找此类中有没有与此属性对应的set 方法，假如有就优先执行set 方法。<br>
    > &emsp;如果没有则直接通过反射为属性赋值。<br>
    > &emsp;如果spring 发现某个set 方法上有此注解，则会直接调用 set 方法为属性赋值。<br>
    >> &emsp;默认情况下，首先按变量名称去查找相应的set 方法<br>
    >> &emsp;如果没有找到，再按类型去查找对象的bean 对象<br>

        @Resource
        private UserDao userDao;
    > 为其指定名字<br>
    >> 指定名字了则只按名字查找，未找到则报错，不会再按类型去查找bean 对象。<br>

        @Resource(name="userDaoImpl")
        private UserDao userDao;

    > 用在set 方法上面<br>

        @Resource
        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }
    - **<span style="color:red">需要注意:</span>**
        > &emsp;在全用`@Resource(name="impl")` 注解的时候，它首先会去找带参构造函数对应的那个bean 对象。如果你将它注解在变量上面，而此时这个带参构造函数也存在。还有一个条件就是这个bean 对象不只一个的话。那么最终会报错，说是有两个对象，不清楚你要哪一个。<br>
        > &emsp;所以在这里如果你想把`@Resource(name="impl")` 注解在变量上面，那么你就不能添加对应的带参构造函数，以避免出错。<br>

- `@Autowired` 与 `@Resource` 使用总结: 
    > 位置使用说明
    > 1) `@Autowired/@Qualifier` 应用在属性或构造方法上
    > 2) `@Resource`一般应用在属性火set方法上.
    > 
    > 应用规则说明:
    > 
    > 通过`@Autowired`实现对象属性值的注入默认是按属性类型进行值的注入,假如: 类中提供了与此属性有对应关的系构造函数则执行这个构造函数直接对属性
    > 
    > 初始化,如果没有,底层通过反射获得属性类型以后,然后从容器中查找与此类型匹配的对象为其注入值.当按类型进行注入时,假如容器中存在多个类型相同的对象时可能会注入失败,此时还可以借助`@Qualifier`这个注解指定
    > 
    > 按名字进行注入(提示:在使用`@Qualifier`注解时前提必须已经使用了`@Autowired`注解),假如没有使用`@Qualifier`注解,此按默认属性名进行查找.
    > 
    > `@Resource` 註解一般用在屬性或set方法上用于为对象参数赋值
    >
    > 规则是假如这个注解中指定了名称,则只按注解中的name属性对应值查找对象,然后进行值得注入.假如注解中没有指定名称,先按变量名进行查找,假如没找到,则按类型查找.假如Spring发现某个set方法上有此注解,则会直接调用set方法为属性赋值.

- 延迟加载策略注解`@Lazy(value=true)`

        @Lazy(value=true)
        @Repository
        public class MessageDaoImpl implements MessageDao {
        }

- `@Value` 注解
    > 从配置文件中取数据，以及表达式

    