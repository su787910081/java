package cn.tedu.authinput;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

// 继承自Partitioner 实现分区逻辑
// mapper 的逻辑处理完成之后会先走到分区处理类的分区处理方法。然后再到Combiner 之后到Reducer
// 范型是mapper 的后两个范型，是mapper 的写出的key 和alue 的类型，
// 如果不实现分区逻辑，那么默认使用的是HashPartitioner
// 分区的返回值是整数类型，它的值必须是以0 开始递增的。递增的值不能 > numPartitions(方法参数2，reduceTask 的个数)
// ReduceTask 的个数在Driver 中指定
public class NamePartitioner extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text key, IntWritable value, int numPartitions) {
        return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;
    }

}
