## mvn 相关命令参数

```sh
# mvn 命令添加变量全用 -D 参数
# 该参数的值将会应用到 pom.xml 中的使用 <properties> 指定的变量与值
mvn clean package -D maven.test.skip=true

# 普通打包，跳过单元测试
mvn clean package -D maven.test.skip=true

# 通过-P 指定打包的profile
# 这个-P 所指定的profile 还会应用到 spring配置文件中的spring.profiles.active 的值
# 问题：-P 是否可以同时指定多个(如：-P dev -P common)，如果同时指定了多个，那么spring.profiles.active 的值是什么？
# 另外spring 是否会同时激活这两个profile
mvn clean package -D maven.test.skip=true -P dev

# 通过 maven.repo.local 来指定jar 包的安装目录
mvn clean install -D maven.test.skip=true -P dev -D maven.repo.local=/home/juven/myrepo/

# 通过 -s 来使用本地的settings.xml 来指定打包的配置
mvn clean install -D maven.test.skip=true -s E:\java\apache-maven-3.6.3\conf\settings.xml
```

```sh
# 在windows 中使用mvn 命令时，最好用call，不然mvn 后面的命令将不会执行，转为mvn 也是一个bat 脚本
call mvn clean package -D maven.test.skip=true
```


## pom.xml 的相关配置

```xml

<!-- 在这个标签下面都是一些自定义的属性及其值，在后面可以通过${spring-boot.version} 的形式引用到它 -->
<!-- 同时还可以在命令行对其值进行变更, 如：mvn -D spring-boot.version=2.0.0 -->
<properties>
    <spring-boot.version>2.2.0</spring-boot.version>
</properties>
```

