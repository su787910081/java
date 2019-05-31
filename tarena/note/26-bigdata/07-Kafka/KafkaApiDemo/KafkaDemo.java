package cn.tedu.suyh;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.security.JaasUtils;
import org.apache.kafka.common.serialization.StringDeserializer;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;

import org.junit.Test;

public class KafkaDemo {

    public static final String KAFKA_SERVERS = "HBase01:9092,HBase02:9092,HBase03:9092";
    public static final String ZK_SERVERS = "HBase01:2181,HBase02:2181,HBase03:2181";

    // 生产者，生成数据
    @Test
    public void producer() throws ExecutionException {
        System.out.println("KafkaDemo.producer()");
        Properties props = new Properties();
        // 这两个序列化类型是固定的
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVERS);

        // props.put("acks", "all"); // 至少一次语义(默认使用此配置)
        // props.put("acks", "0"); // 至多一次语义

        // 精确一次语义由下面两行设置一起配合使用
        props.put("acks", "all");
        props.put("enable.idempotence", "true");

        Producer<Integer, String> kafkaProducer = new KafkaProducer<Integer, String>(
                props);
        
        DecimalFormat df = new DecimalFormat("000");
        for (int i = 0; i < 100; i++) {
            String strNumber = df.format(i);
            System.out.println(strNumber);
            ProducerRecord<Integer, String> message = new ProducerRecord<Integer, String>(
                    "t2", strNumber); // 主题 + 数据
            kafkaProducer.send(message);
        }

        System.out.println("KafkaDemo.producer()");

        while (true)
            ;
    }

    // 创建主题
    @Test
    public void create_topic() {

        ZkUtils zkUtils = ZkUtils.apply(ZK_SERVERS, 30000, 30000,
                JaasUtils.isZkSecurityEnabled());
        // 创建一个单分区单副本名为t1的topic
        // --①参：zk连接参数 ②参:主题名 ③参:分区数 ④参:副本数
        // 后面两个参数写死就好。一般的使用用不到修改
        AdminUtils.createTopic(zkUtils, "t2", 3, 1, new Properties(),
                RackAwareMode.Enforced$.MODULE$);
        zkUtils.close();
        
        System.out.println("KafkaDemo.create_topic()");
    }

    // 删除主题
    @Test
    public void delete_topic() {
        ZkUtils zkUtils = ZkUtils.apply(ZK_SERVERS, 30000, 30000,
                JaasUtils.isZkSecurityEnabled());
        // 删除topic 't2'
        AdminUtils.deleteTopic(zkUtils, "t2");
        zkUtils.close();
        
        System.out.println("KafkaDemo.delete_topic()");
    }

    // 消费数据
    @Test
    public void consumer_1() {
        Properties props = new Properties();
        // --设置kafka的服务列表
        props.put("bootstrap.servers", KAFKA_SERVERS);
        // --指定当前的消费者属于哪个消费者组 g1是自己定的
        props.put("group.id", "g1");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // --指定消费者消费的主题，可以指定多个
        consumer.subscribe(Arrays.asList("enbook", "t2"));

        try {
            while (true) {
                // --消费者从分区队列中消费数据，用到poll阻塞方法，如果没有数据可以消费，则一直阻塞
                ConsumerRecords<String, String> records = consumer
                        .poll(Long.MAX_VALUE);
                for (ConsumerRecord<String, String> record : records)

                    System.out.println(
                            "g1组c1消费者,分区编号:" + record.partition() + ", offset:"
                                    + record.offset() + ":" + record.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    // 消费数据
    @Test
    public void consumer_2() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_SERVERS);
        // --指定当前的消费者属于哪个消费者组 g1是自己定的
        props.put("group.id", "g1");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        // 消费者收到消息后，0.1 s 后会自动提交，这层语义为最多一次 -- 这也是默认处理方式
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "101");

        // 如果要达到至多一次则需要关闭自动提交
        // 并且我们在处理完成之后手动执行提交操作(commitAsync())
        // props.put("enable.auto.commit", "false"); // 关闭自动提交
        // consumer.commitAsync(); // 手动提交

        // 精确一次提交，前提是生产者也需要是精确一次生产
        // 同时添加下面三个配置
        // props.put("enable.auto.commit", "false"); // 关闭自动提交
        // props.put("processing.guarantee", "exact_once");
        // props.put("enable.idempotence", "true");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // --指定消费者消费的主题，可以指定多个
        consumer.subscribe(Arrays.asList("enbook", "t2"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer
                        .poll(Long.MAX_VALUE);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(
                            "g1组c2消费者,分区编号:" + record.partition() + "offset:"
                                    + record.offset() + ":" + record.value());

                consumer.commitAsync(); // 手动提交
            }
        } catch (Exception e) {
        } finally {
            consumer.close();
        }
    }

    @Test
    public void testE() {

        int a = Math.abs("console-consumer-45471".hashCode()) % 50;
        System.out.println(a);

    }

}
