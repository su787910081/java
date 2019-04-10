# 记录学习过程中遇到的一些问题


## mapper.xml 中resyltType="map" 

- `resyltType="map" `
    > 在Controller 实现类中有这样一个方法
    >> 下面这段代码出现了编译错误：

        四月 10, 2019 11:32:03 上午 org.apache.catalina.core.StandardWrapperValve invoke
        严重: Servlet.service() for servlet [dispatcherServlet] in context with path [/CGB-JT-SYS-V1.01] threw exception [Request processing failed; nested exception is java.lang.ClassCastException: java.util.HashMap cannot be cast to com.jt.sys.pojo.SysRole] with root cause
        java.lang.ClassCastException: java.util.HashMap cannot be cast to com.jt.sys.pojo.SysRole

    >>> 重点在这里，它有一个类型转换错误: `java.util.HashMap cannot be cast to com.jt.sys.pojo.SysRole] with root cause`

        @RequestMapping("doFindPageObjects")
        @ResponseBody
        public List<SysRole> doFindPageObjects(@RequestParam("pageCurrent") String pageCurrent) {
            
            List<SysRole> list = sysRoleService.findPageObjects();
            for (SysRole role : list) {
                System.out.println(role);
            }
            return list;
        }
    
    > 在实体类`SysRole` 中的`getCreatedTime()` 方法
    >> 下面这段代码出现的问题是，在控制台没有相关的输出

        public Date getCreatedTime() {
            System.out.println("SysRole.getCreatedTime()");
            return createdTime;
        }
    
    > 最终找到的问题是：
    >> 在相关的mapper.xml 配置文件中有这样一个配置

    >> 最后的问题就出在了`resultType="map"` 这里，把它改成`resultType="sysRole"` 就解决了上面两个问题。

        <select id="findPageObjects" resultType="map">
            select * from sys_roles
        </select>




