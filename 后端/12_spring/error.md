

- 错误: `No converter found for return value of type: class java.util.HashMap` 
    > 将一个map 数据直接从`Controller` 实现中返回出去时，如果没有添加`Json` 相关的处理包的话，将会出现这样的错误。<br>
    > 那么我们添加其依赖的jar 包就可以了.<br>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.8.5</version>
        </dependency>



