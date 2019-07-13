package cn.tedu.authinput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AuthDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // 指定结果文件的基础名
        conf.set("mapreduce.output.basename", "auth");
        // 指定输出结果的分隔符(默认为'\t')
        conf.set("mapreduce.output.textoutputformat.separator", ",");

        Job job = Job.getInstance(conf, "JobName");
        job.setJarByClass(AuthDriver.class);
        job.setMapperClass(AuthMapper.class);
        job.setReducerClass(AuthReducer.class);

        // 指定mapper 输出的KEY与VALUE 的类型，如果与Reducer 的输出类型一致，则可以只设置Reducer 的
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Student.class);

        // 指定Reducer 的输出Key 与Value 的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 自定义 inputformat
        // 指定Mapper 输入的处理方式，也就是将分片做怎么的处理，然后将处理的结果作为mapper 的输入
        job.setInputFormatClass(AuthInputFormat.class);

        // 自定义 Combiner -> AuthReducer
        // MapTask在算完之后会将结果先进行合并，然后再将合并后的数据发给ReduceTask
        // Combine：减少数据量但是不影响结果
        // 我们可以实现一个自定义的合并类，这里我直接使用Reducer
        job.setCombinerClass(AuthReducer.class);

        // 多源输入，可以为每一个输入源指定它的处理mapper
        MultipleInputs.addInputPath(job,
                new Path("hdfs://192.168.220.130:9000/txt/score3.txt"),
                TextInputFormat.class);
        MultipleInputs.addInputPath(job,
                new Path("hdfs://192.168.220.130:9000/mul2/words.txt"),
                TextInputFormat.class, AuthMapper.class);
        // 单源输入
        // org.apache.hadoop.mapreduce.lib.input.FileInputFormat.setInputPaths(job,
        // new Path("hdfs://192.168.220.130:9000/txt/score3.txt"));
        FileOutputFormat.setOutputPath(job,
                new Path("hdfs://192.168.220.130:9000/authinput"));

        if (!job.waitForCompletion(true)) {
            return;
        }

        // TODO:
        // 如果我们需要用到第一个job 的结果作为第二个job 的输入的话，我们可以在这里加一个链式处理。
        // 即：在这里按上面的一个JOB 做一样的处理，只是将输入源设置为JOB1的输出
        // ...
    }

}
