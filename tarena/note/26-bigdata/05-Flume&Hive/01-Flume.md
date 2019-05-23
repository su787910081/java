


- ## 基本概念
    - > Event: 
        > - 在Flume 中，将它的每一个日志封装成一个Event 对象。也就意味着在Flume中传输的Event 对象。Event 对象展现形式是一个json 串，这个json 串分为两个部分: headers 和body
    - > Agent: 
        > - 在Flume中，传输数据用的组件就是Agent.
        > - 一个source 可以绑定多个Channel 但是一个Channel 只能绑定一个Sink





- 启动
    - > 命令 `sh flume-ng agent -n a1 -c ../conf/ -f ../data/basic.conf -Dflume.root.logger=INFO,console`


- ## nc 模拟发TCP 数据
    - > `nc 192.168.220.146 8090`

- ## avro 
    - > avro 服务器
        > - `../bin/flume-ng agent -n a1 -c ../conf/ -f ./avrosource.conf -Dflume.root.logger=INFO,console`
    - > 模拟客户端发数据
        > - `sh flume-ng avro-client -H 0.0.0.0 -p 8070 -F /root/software/apache-flume-1.6.0-bin/data/a.txt`
        >> - a.txt 文件中可以是任意数据，这个数据将直接被发到服务器



curl -X POST -d '[{"headers":{"class":"big1812","classroom":"502"},"body":"hello big1812"}]'  http://0.0.0.0:8070

