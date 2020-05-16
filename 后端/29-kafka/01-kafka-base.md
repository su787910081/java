
记录：
	1. 消费者组中的消费者数量不应该多于主题的分区数量；
		多了的消费者将没有消息可消费，存在浪费。

Zookeeper 注册消息
	Kafka 节点使用同一套zk 那么就会形成同一个集群
	Kafka 消费者消费数据的每几条，会被zk 记录并保存起来，当消费者再次连上来时，才会继续上次的位置进行消费。
		在0.9 版本之前存储在ZK 中，在之后，存储在Kafka Broker 服务器本地磁盘。

Broker
	kafka 集群的服务器节点，每一个节点就叫做一个broker

kafka 中的Leader 和Follower 是针对于主题来说的，每一个主题都有对应的Leader 和Follower
	但是不管是消费都还是生产者都只访问Leader，并不会读写分节点访问 。

kafka 中是有主题的，主题是有分区的。分区是有复本的。

分区
	一般同一个主题对应着多个分区。
	我们可以对这些分区配置复本。

消费者组的概念
	多个消费者放到一个组里面
	一个主题中的某一个分区，只能被同一个消费者主里面的某一个消费者消费。
	所以如果消费者组里面消费者的数量多于分区数量，则多于的消费者是没有任何消息可供消费。


kafka 启动与配置
	config/server.properties

	# 以占用控制台的方式启动
	./kafka-server-start.sh ../config/server.properties
	# 以守护进程的方式启动
	./kafka-server-start.sh -daemon ../config/server.properties
	./kafka-server-stop.sh
