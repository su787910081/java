

## 事务控制
1. 添加maven 依赖

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>4.3.9.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.3.9.RELEASE</version>
		</dependency>

2. 添加声明式事务处理配置(spring)

        <!-- ****** Spring 声明式事务处理 ****** -->
        <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                <property name="dataSource" ref="dataSource"/>
        </bean>
        <!--设置注解驱动的事务管理  -->
        <tx:annotation-driven transaction-manager="txManager"/>

3. 在业务处理类上面添加注解`@Transactional`
    - 将这个注解添加在类上面，则指定这个类的所有方法都默认事务处理。当出现`RuntimeException` 异常时，自动回滚。



- 事务传播特性
> - 事务方法之间相互调用时，事务的传播方式。
> - propagation	事务的传播行为，默认值为 REQUIRED(重点掌握)
> - 表示此方法假如被其它的事务方法调用，则它会运行在那个已经存在 的事务中。
> - 多个方法之间相互调用，且都存在事务处理。那么被调用的一方将不会添加新的事务处理。
> ![](./img/propagation-required.png)

- 事务隔离级别

> - 当多个事务并发执行时，可通过设置事务的隔离级别保证事务的完整性，一致性。
> - 事务的隔离级别从低到高有如下几种方式：
> 1. READ_UNCOMMITTED (此级别可能会出现脏读)
> 2. READ_COMMITTED(此级别可能会出现不可重复读)
> 3. REPEATABLE_READ(此级别可能会出现幻读)
> 4. SERIALIZABLE(多事务串行执行)















