
- ## pom.xml 指定BUILD 源文件以及生成的目标文件的路径

- ## avro 序死化与反序死化
    - > 以 .avsc 为后缀的文件
        >> <details>
        >> <summary><mark><font color=darkred>Person.avsc</font></mark></summary>
        >> 
        >>      {
        >>          "namespace":"avro.pojo",    // 表示包名
        >>          "type":"record",    // 声明类
        >>          "name":"Person",    // 类名
        >>          "fields":
        >>          [
        >>              {"name":"name","type":"string"}, // 声明了一个属性：String name
        >>              {"name":"age","type":"int"},
        >>              {"name":"gender","type":"boolean", "default": "false"}  // 指定默认值
        >>          ]
        >>      }
        >> </details>
        >>
    
- ## avro RPC 过程调用
    - > RPC 编写以 .avdl 为后经的文件
        >> <details>
        >> <summary><mark><font color=darkred>Calc.avdl</font></mark></summary>
        >> 
        >>      @namespace("avro.service")
        >>      protocol CalcService {
        >>          int add(int i, int j);
        >>          double max(double i, double j);
        >>      }
        >> 
        >> </details>
        >> 




- ## 编译avro 文件，生成java 源文件
    - > `pom.xml  -->  run as   -->   maven generate-sources`
        >> - 这里会根据pom.xml 文件中配置的目录，将源文件目录中的所有源文件构造成相对应的目标源文件，放到目标目录中。
    - > 然后刷新就会找到生成的新的JAVA 源文件

