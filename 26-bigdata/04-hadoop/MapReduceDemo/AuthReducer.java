package cn.tedu.authinput;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AuthReducer extends Reducer<Text, Student, Text, IntWritable> {

    public void reduce(Text key, Iterable<Student> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (Student val : values) {
            sum += val.getMath() + val.getEnglish();
        }
        context.write(key, new IntWritable(sum));
    }

}

