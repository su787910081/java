package cn.tedu.authinput;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

// FileInputFormat<K, V> 提供的两个泛型是提供给mapper 的键与值的类型
public class AuthInputFormat extends FileInputFormat<Text, Text> {

    // 针对切片提供输入流来读取切片中的数据
    // 这里返回一个RecordReader<K, V>
    // 它的泛型也是提供给Mapper 的键与值 的类型
    // 我们需要实现这个RecordReader<K, V> 抽象类的抽象方法
    @Override
    public RecordReader<Text, Text> createRecordReader(InputSplit split,
            TaskAttemptContext context)
                    throws IOException, InterruptedException {
        return new AuthReader();
    }
}

// 实现一个抽象类RecordReader<K, V>，泛型是给到Mapper 的键和值
// 得到一个分片文件，并处理这个分片文件。
class AuthReader extends RecordReader<Text, Text> {
    private LineReader reader;

    private Text key;
    private Text value;
    private Text separator = new Text(" ");

    // 初始化方法，主要获取输入流。然后通过这个流读取分片中的数据并处理，然后输出到mapper 中。
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        // 将split 转化为FileSplit 注意导入的包
        FileSplit fsplit = (FileSplit) split;
        // 获取到要去读的实际文件
        Path p = fsplit.getPath();

        // 连接HDFS，
        // FileSystem 默认情况下使用的是当前平台的文件系统，如Windows 的就是: file:///
        // FileSystem.get(context.getConfiguration());
        // 而我们要连接的是HDFS，所以这里需要用到URI
        FileSystem fs = FileSystem.get(URI.create(p.toString()), 
                context.getConfiguration());

        // 获取输入流，并将其包装成一个字符流。我们要按行来读取。
        FSDataInputStream in = fs.open(p);
        reader = new LineReader(in);
    }

    // 在MapReduce 中， 在调用map 方法之前 ，都会调用这个方法判断 是否还有数据 需要进行处理
    // 如果有数据，则调用MAP方法，如果没有数据，则说明数据已经处理完。
    // 也就意味着在这个方法中需要判断 还有没有数据 提供给MAP 方法
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        Text tmp = new Text();
        // 返回值表示读取到的字符个数
        int charSize = reader.readLine(tmp);
        if (charSize == 0) {
            return false;
        }
        
        // 这里直接将读到的数据处理成mapper 需要的KEY VALUE
        key = new Text();
        key.set(tmp.getBytes(), 0, tmp.getLength());
        // 第二次读取，读到的是value
        reader.readLine(tmp);
        value = new Text();
        value.set(tmp.getBytes(), 0, tmp.getLength());
        // 将两次的读取结果 用空格进行分隔

        value.append(separator.getBytes(), 0, separator.getLength());
        // 第三次读取
        reader.readLine(tmp);
        value.append(tmp.getBytes(), 0, tmp.getLength());

        return true;
    }

    // 获取key
    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    // 获取value
    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    // 获取map 方法的执行进度 这里没有实现。
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    // 回收资源
    @Override
    public void close() throws IOException {
        if (reader != null)
            reader.close();
        
        reader = null;
        key = null;
        value = null;
    }

}
