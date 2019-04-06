

## mybatis 基础
1. 创建`maven java` 项目

2. 添加`mybatis` maven依赖

3. 添加核心配置文件`mybatis-config.xml`
    > 开发环境配置

        <configuration>
            <environments default="development">
                <environment id="development">
                    <transactionManager type="JDBC" />
                    <dataSource type="POOLED">
                        <property name="driver" value="com.mysql.jdbc.Driver" />
                        <property name="url" value="jdbc:mysql:///test" />
                        <property name="username" value="root" />
                        <property name="password" value="password" />
                    </dataSource>
                </environment>
            </environments>
        </configuration>

    > 添加`mapper` 配置

        <configuration>
            <mappers><mapper resource="mapper/SysUserMapper.xml" /></mappers>
        </configuration>

    <a name="aliase"></a>
    > 为`mapper` 配置中的类名添加<span style="color:red">**别名**</span>

    >> 为某一个类添加别名: 
    
        <configuration>
            <typeAliases>
                <typeAlias type="com.project.sys.entity.SysUser" alias="sysUser"/>
            </typeAliases>
        </configuration>

    >> 为某一个包下所有类添加别名: 默认别名为类首字母小写

        <configuration>
            <typeAliases>
                <package name="com.project.sys.entity"/>
            </typeAliases>
        </configuration>

4. 添加`mapper` 映射配置文件`mapper/SysUserMapper.xml`
    > mybatis 中的映射文件，对于这个映射文件必须有一个`mapper` 根元素，这个根元素中必须定义个命名空间 `namespace`<br>

    > 所有的SQL 语句都必须要有一个id

    >> SQL 查询

    >>> 对于查询语句，其中`resultType="map"` 表示查询结果会封装到对应的map类型中，一行记录对应一个map

        <mapper namespace="com.project.sys.dao.SysUserDao">
            <select id="findUsers" resultType="map">select * from sys_users</select>
        </mapper>

    >>> 为查询语句指定参数，以及参数类型

        <mapper namespace="com.project.sys.dao.SysUserDao">
            <select id="findUserById" parameterType="int" resultType="map">
                select * from sys_users where id = #{id}
            </select>
        </mapper>

    >>> 将结果指定封装到一个实体类中

    >>> `resultType="com.project.sys.entity.SysUser"` 可以在mybatis-config.xml 中<a href="#aliase">配置别名</a>

        <mapper namespace="com.project.sys.dao.SysUserDao">
            <select id="findObjectById" resultType="com.project.sys.entity.SysUser">
                select * from sys_users where id = #{id}
            </select>
        </mapper>

    >> 插入`insert` 语句

    >>> 所有的insert、update、delete 都有一个默认的返回类型为int，表示影响的行数

        <mapper namespace="com.project.sys.dao.SysUserDao">
            <insert id="insertObject" parameterType="sysUser">
                insert into sys_users(id, username, password, phone, createdDate, modifiedDate) 
                values(null, #{username}, #{password}, #{phone}, now(), now())
            </insert>
        </mapper>

## 用法
- 基本使用
    > 有且仅有一个`SqlSessionFactory`<br>

        import org.apache.ibatis.io.Resources;
        import org.apache.ibatis.session.SqlSession;
        import org.apache.ibatis.session.SqlSessionFactory;
        import org.apache.ibatis.session.SqlSessionFactoryBuilder;

        public void testUseage() throws IOException {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = factory.openSession();
            List<Map<String, Object>> list = session.selectList(
                    "com.project.sys.dao.SysUserDao.findUsers");

            session.close();
        }

- 通过接口来使用
    > 首先需要添加一个接口`com.project.sys.dao.SysUserDao`

    >> 我们只需要添加一个接口，不需要去实现它，mybatis 会为我们实现这个接口。

        public interface SysUserDao {
            List<Map<String, Object>> findUsers();
            Map<String, Object> findUserById(Integer id);
            int insertObject(SysUser entity);
        }

    > 接口的包名`com.project.sys.dao.SysUserDao`需要与`mapper/SysUserMapper.xml` 配置文件中根元素里面的的`namespace` 的值保持一致

        <mapper namespace="com.project.sys.dao.SysUserDao"></mapper>

    > 接口中方法名，需要与mapper 文件中sqlid(`select id="findUsers"`) 一致

        <select id="findUsers" resultType="map">
            select * from sys_users
        </select>

    > 使用的时候，参考如下示例代码: 

		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		SysUserDao sysUser = session.getMapper(SysUserDao.class);
		List<Map<String, Object>> list = sysUser.findUsers();
		session.close();


