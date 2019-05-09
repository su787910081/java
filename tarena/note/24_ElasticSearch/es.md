



- ## CURL 给ES 的命令
    > - 通过curl 命令给es 发送一个指令，配置其只读
    >> - `curl -XPUT -d '{"blocks.read":true}' http://10.42.170.247:9200/index02/_settings`
    >> - `"blocks.read":true`







