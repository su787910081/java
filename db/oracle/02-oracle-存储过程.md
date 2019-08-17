


- ## 学习博客
    - > 网址
        > - `https://blog.csdn.net/uniqueweimeijun/article/details/82668835`

- ## 权限
    - > oracle 中创建和使用存储过程需要授权，如果没有授权则会报错
        - > `ORA-0131: Insufficient privileges`

- ## PL/SQL 工具中执行存储过程
    - > `EXECUTE PACKAGE_NAME.PROC_NAME(PARAM1, PARAM2, ...)`
    - > 还可以使用`BEGIN END `块

- ## 存储过程中动态执行一个SQL 字符串
    - > 执行一条查询，并将结果存到一个变量中
        > - `EXECUTE IMMEDIATE ls_sql INTO ln_cnt;`
    - > 执行一条SQL，并为其传参
        > - `EXECUTE IMMEDIATE str_sql USING IN in_param1, IN in_param2, OUT out_param1, ...`
    - > 同时要传参，还要获取结果的。`INTO` 语句要在 `USING` 后面
        >>      EXECUTE IMMEDIATE 'select dname, loc from dept where deptno = :1'
        >>          INTO l_nam, l_loc
        >>          USING l_dept ;


- ## 动态执行SQL，并为其传参







