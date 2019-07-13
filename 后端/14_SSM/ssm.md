
## 框架图

![ssm](./img/ssm.png)

## SSM

- Spring + Spring MVC + Mybatis

1. 创建项目，搭建环境
    > 创建maven 项目

    > 处理小红叉

    > 项目编码

    > `Project Facets` 修改
2. 添加MAVEN 依赖
    > `pom.xml` 依赖荐的添加
3. 配置spring mvc 相关环境
    - `web.xml`
    - 测试环境
4. 数据库连接池(DRUID)

        <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource"
            init-method="init" destroy-method="close">
            <property name="driverClassName" value="com.mysql.jdbc.Driver" />
            <property name="url" value="jdbc:mysql:///jt_sys" />
            <property name="username" value="root" />
            <property name="password" value="suyunfei" />
        </bean>

5. mybatis 配置
6. 添加mapper 配置
7. 对应的mapper 接口
8. 创建service 接口以及实现，从mapper 接口中获取数据库中的数据
9. 创建controller 从service 接口中获取相关数据





