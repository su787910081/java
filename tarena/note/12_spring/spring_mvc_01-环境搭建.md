

## 搭建环境
- 创建 `Maven Web Project`
- 创建`Maven Project` 项目
    > - &emsp;需要指定`Group Id`以及`Artifact Id` <br>
    > - &emsp;然后修改`Packaging` 为 `war` 格式<br>
    > - &emsp;完成之后在会有错误，在`pom.xml` 文件上面有一个小红叉.<br>
- 修改错误(`pom.xml` 上面的小红叉)
    > - &emsp;在`window --> show view --> project explorer` 模式下<br>
    > - &emsp;右键`Deployment Descriptor: ...`  --> `Generate Deployment Descriptor Stub`<br>
    > - &emsp;如果`src/main/webapp/` 下面有了一个`WEB-INF` 目录说明正确了<br>
- 运行时环境以及编译环境
    > - &emsp;右键项目  --> `Properties` -->  `Project Facets` --> 修改对象的版本  `Java`  -->  1.8<br>
    > - &emsp;右键项目  --> `Properties` -->  `Targeted Runtimes` --> 选择一个`tomcat`  如果没有就创建一个<br>

## 项目依赖
- 在`pom.xml` 中添加Maven 依赖

        <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>4.3.9.RELEASE</version>
		</dependency>

