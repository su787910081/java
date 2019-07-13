

- `Spring mvc` 中`Controller` 是怎么被 `SimpleUrlHandlerMapping` 得到的并管理的?
    > `Spring MVC` 中的每一个`Controller` 对象都是一个`JavaBean` 对象，我们一般通过注解`@Controller` 标记它为一个`Bean` 对象，并交由`Spring` 进行管理。<br>
    > 而`SimpleUrlHandlerMapping` 它会扫描存在`@RequestMapping` 注解的方法以及类对象。利用定义的值来添加`url` 映射关系，并存储起来。<br>
    > 最后`DispatcherServlet` 通过`SimpleUrlHandlerMapping` 来找到客户端 请求的URL 所对应的`Controller`处理器来处理请求。<br>

- `Spring MVC` 中的这些`Controller` 对象，是在哪里被配置给`Srping` 进行管理的。
    > 我不知道`Spring MVC` 是总是WEB 项目，还是会有其他类型的项目。这里我就以WEB 项目来说。<br>
    > 对于WEB 项目，我们都会有一个`web.xml` 的配置文件，`Controller` 对象，就是在这里配置给`Spring` 进行管理的。<br>
    > 在`web.xml` 中我们会配置一个`DispatcherServlet` ，并为他指定了初始化参数`contextConfigLocation` 其值就是一个xml 配置文件。在这个文件中，我们配置了`bean` 对象的扫描。通过扫描`Spring` 就拿到了所有的`JavaBean`对象。<br>
    >> 特别地：`Spring MVC` 的注解 功能以及视图解析器都是在这个配置文件中进行配置的。<br>


- 了解 LRU 算法(lruCache  DisLruCache).


