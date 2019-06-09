


- ## ES 介绍
    - > 基于lucene 的搜索服务，采用JAVA 编写。使用lucene 构建索引，提供搜索等功能
    - > <mark>ES 只允许普通用户启动</mark>
        > - 所以我们需要先创建一个普通用户，一般都是以`es` 为名

- ## CURL 给ES 的命令
    > - 通过curl 命令给es 发送一个指令，配置其只读
    >> - `curl -XPUT -d '{"blocks.read":true}' http://10.42.170.247:9200/index02/_settings`
    >> - `"blocks.read":true`


curl -XGET HTTP://127.0.0.1:9200

- > 新建索引
    > - curl -XPUT http://127.0.0.1:9200/index01





