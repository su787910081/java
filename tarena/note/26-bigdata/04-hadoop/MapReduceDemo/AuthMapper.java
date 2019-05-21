package cn.tedu.authinput;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 这里用我们片定义实现的文件输入处理格式化(AuthReader)
public class AuthMapper extends Mapper<Text, Text, Text, IntWritable> {

    public void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {
        String[] arr = value.toString().split(" ");
        int sum = Integer.parseInt(arr[1]) + Integer.parseInt(arr[3]);
        
        context.write(key, new IntWritable(sum));
    }

}
