

## spring 也有一个安全框架
- Spring Security 安全框架
    > 有空的时候需要去了解一下它

## shiro 
- 基本架构图
    > - shiro 框架进行权限管理时涉及到的一些核心对象: 
    >> 1.  认证管理对象(`Athenticator`)
    >> 1.  授权管理对象(`Authorizer`)
    >> 1.  会话管理对象(`Session Manager`)
    >> 1.  缓存管理对象(`Cache Manager`)
    >> 1.  加密处理对象(`Cryptography`)
    >> 1.  Realm 管理对象(领域对象：负责处理认证和授权领域的数据访问问题)

    ![](./img/shiro_base.png)

- 认证流程

    ![](./img/shiro_authenticator_proccess.png)

- 授权流程

    ![](./img/shiro_authorizer_proccess.png)



- 整合Shiro 框架
    1. 添加maven依赖
        <details>
        <summary><mark><font color=darkred>Shiro maven 依赖</font></mark></summary>

		>       <dependency>
		>       	<groupId>org.apache.shiro</groupId>
		>       	<artifactId>shiro-spring</artifactId>
		>       	<version>1.3.2</version>
		>       </dependency>
		>       <dependency>
		>       	<groupId>org.apache.shiro</groupId>
		>       	<artifactId>shiro-ehcache</artifactId>
		>       	<version>1.3.2</version>
		>       </dependency>
        </details>

    2. 添加web.xml 过滤器配置
        <details>
        <summary><mark><font color=darkred>shiro 过滤器 配置</font></mark></summary>

        > - 参数名为一个spring bean 对象，这个对应spring 配置文件中的配置id
        >>     <filter>
        >>         <filter-name>DelegatingFilterProxy</filter-name>
        >>         <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
        >>         <init-param>
        >>             <param-name>targetBeanName</param-name>
        >>             <!-- 这个名字需要与spring bean 里面配置的bean 对象的ID 值相对应 -->
        >>             <param-value>shiroFilter</param-value>
        >>         </init-param>
        >>     </filter>
        >>     <filter-mapping>
        >>         <filter-name>DelegatingFilterProxy</filter-name>
        >>         <url-pattern>/*</url-pattern>
        >>     </filter-mapping>
        </details>
    3. 添加spring 配置
        <details>
        <summary><mark><font color=darkred>shiro 工厂bean 配置</font></mark></summary>

        >  - 添加一个自定义的Realm bean 对象
        >>     <bean id="userRealm" class="com.jt.sys.service.realm.ShiroUserRealm">
        >>         <!-- 凭证算法匹配器 -->
        >>         <property name="credentialsMatcher">
        >>             <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
        >>                 <property name="hashAlgorithmName" value="MD5"></property>
        >>             </bean>
        >>         </property>
        >>     </bean>
        > - 配置shiro 安全管理器, 是shiro 框架的核心安全管理器
        >>     <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        >>         <property name="realm" ref="userRealm"></property>
        >>     </bean>
        > - shiro工厂bean配置(这个id 值提供给web.xml 中配置的过滤器的参数值使用 )
        >>     <bean id="shiroFilter" class= "org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        > `shiro的核心安全接口`
        >>         <property name="securityManager" ref="securityManager" />
        > `要求登录时的连接`
        >>         <property name="loginUrl" value="/loginUI.do"></property>
        > `登录成功后要跳转的连接(这个也可以在程序中处理)`
        >>         <!-- <property name="unauthorizedUrl" value="/default.html"></property> -->
        > `访问未对其授权的资源时,要跳转的连接`
        >>         <property name="filterChainDefinitions">
        >>             <value>
        > `允许匿名访问的资源，这个必须要写在前面`
        >>                 /bower_components/** = anon
        >>                 /build/** = anon
        >>                 /dist/** = anon
        >>                 /plugins/** = anon
        >> 
        > `可以匿名访问的路径, 例如：验证码、登录连接、退出连接`
        >>                 /loginUI.do = anon
        >>                 /doLogin.do = anon
        >>                 /indexUI.do = anon
        > `这个logout.do 我们都不需要实现，对于这个项目而言` '='后面的logout 必须是这个字符串，不能是别的
        >>                 /logout.do = logout
        > `剩余其他的路径都必须通过才可以访问，这个要写在最后。`
        >>                /** = authc
        >>             </value>
        >>         </property>
        >>     </bean>
        > - Shiro 生命周期处理器，Shiro 框架会负责管理Shiro 各种组件对象的生命周期
        >>     <bean id="lifecycleBeanPostProcessor" 
        >>        class="org.apache.shiro.spring.LifecycleBeanPostProcessor"></bean>
        > - 启用shiro 注解权限检查
        >>     <bean
        >>        class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
        >>          depends-on="lifecycleBeanPostProcessor"></bean>
        >>     <bean
        >>       class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        >>         <property name="securityManager" ref="securityManager"></property>
        >>     </bean>

        </details>

    4. 创建用户，以及其密码的加密
        <details>
        <summary><mark><font color=darkred>随机盐值加密处理</font></mark></summary>

        > - 采用随机值作为盐值进行加密处理然后保存到数据库中
		>>     String pwd = entity.getPassword();
		>>     if (!StringUtils.isEmpty(pwd)) {
		>>         ByteSource source = ByteSource.Util.bytes(pwd.getBytes());
		>>         String salt = UUID.randomUUID().toString();
		>>         SimpleHash simpleHash = new SimpleHash("MD5", source, salt);
		>>         entity.setPassword(simpleHash.toHex());
		>>         entity.setSalt(salt);
		>>     }
        </details>
    5. 登录业务实现
        <details>
        <summary><mark><font color=darkred>shiro 工厂bean 配置</font></mark></summary>

        > 登录业务调用主体的登录接口，然后会跳转到自定义的realm 中处理。
        >>     public void login(String username, String password) {
        >>     	Subject subject = SecurityUtils.getSubject();
        >>     	if (subject.isAuthenticated())
        >>     		return;
        >>     	UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        >>     	try {
        >>     		subject.login(token);	// token-->SecurityManager-->realm			
        >>     	} catch (IncorrectCredentialsException ice) {
        >>     		ice.printStackTrace();
        >>     		throw new ServiceException("密码错误");
        >>     	} catch (AuthenticationException ae) {
        >>     		ae.printStackTrace();
        >>     		throw new ServiceException("认证失败");
        >>     	}
        >>     }
        </details>

    6. 实现一个自定义类Realm，继承自`AuthorizingRealm`
        <details>
        <summary><mark><font color=darkred>自定义Realm</font></mark></summary>

        > - 引入包
        >>     import org.apache.shiro.SecurityUtils;
        >>     import org.apache.shiro.authc.AuthenticationException;
        >>     import org.apache.shiro.authc.AuthenticationInfo;
        >>     import org.apache.shiro.authc.AuthenticationToken;
        >>     import org.apache.shiro.authc.SimpleAuthenticationInfo;
        >>     import org.apache.shiro.authc.UsernamePasswordToken;
        >>     import org.apache.shiro.authz.AuthorizationInfo;
        >>     import org.apache.shiro.authz.SimpleAuthorizationInfo;
        >>     import org.apache.shiro.realm.AuthorizingRealm;
        >>     import org.apache.shiro.subject.PrincipalCollection;
        >>     import org.apache.shiro.util.ByteSource;
        >>     import org.springframework.beans.factory.annotation.Autowired;
        >>     import com.jt.sys.dao.SysUserDao;
        >>     import com.jt.sys.pojo.SysUser;
        >>     
        > Component 这个注解可以不加，因为在配置文件中有添加了相应的配置
        >>     @Component
        >>     public class ShiroUserRealm extends AuthorizingRealm {
        >>         @Autowired
        >>         private SysUserDao sysUserDao;
        >>     
        > - 授权(权限)检测方法
        >>         @Override
        >>         protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        >>             System.out.println("ShiroUserRealm.doGetAuthorizationInfo()");
        >>             // 1. 获取用户权限(用户-->角色-->资源)
        >>             // 1.1 获取用户信息(session)
        >>             SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute      >>   ("user");
        >>             // 1.2 根据用户信息获取资源的访问权限
        >>             List<String> list = sysUserDao.findUserPermissions(user.getId());
        >>             Set<String> set = new HashSet<>(list);	// 去重
        >>             System.out.println("permissions: " + set);
        >>             
        >>             // 2. 封装用户权限(AuthorizationInfo)
        >>             SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        >>             info.setStringPermissions(set);
        >>             
        >>             return info;
        >>         }
        >>     
        > - 认证检测(用户身份是否存在，密码是否正确)
        > - 调用流程: `subject.login(token)` --> `securityManager` --> `Authentication` --> `Realm.doGetAuthenticationInfo`
        > - 对应创建用户时的密码加密流程以及方法
        >>         @Override
        >>         protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        >>             UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        >>             String username = upToken.getUsername();
        >>             SysUser user = sysUserDao.findObjectByUserName(username);
        >>             
        >>             ByteSource byteSource = ByteSource.Util.bytes(user.getSalt().getBytes());
        >>             
        >>             SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
        >>                     // 用户身份，已加密的密码，盐值对应的ByteSource, realm 的名字
        >>                     user.getUsername(), user.getPassword(), byteSource, getName());
        >>             
        >>             // 4. 存储用户信息(session)
        >>             SecurityUtils.getSubject().getSession().setAttribute("user", user);
        >>             
        >>             return info;	// 此对象返回给认证了？ 认证器对象
        >>         }
        >>     
        >>     }
        </details>





## 遇到的问题
- 登录时没到服务端
    > 这个时候可以检查一下，是不是过滤器将其过滤掉，让他需要进行认证。因为在这个时候正是在认证的过滤中，所以登录的那个页面需要允许共匿名访问。