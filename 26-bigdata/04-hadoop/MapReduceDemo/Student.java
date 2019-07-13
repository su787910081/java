package cn.tedu.authinput;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

// 实现自定义类需要能够被 序列化，序列化底层使用的是AVRO 协议。HADOOP 为我们提供了简单的处理，只需要实现Writable 接口
// 如果要对其进行排序，那么我们就只需要实现一个WritableComparable<Student> 同时满足序列化与的排序的功能
public class Student implements Writable {

    private String name = "";
    private int math;
    private int english;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(math);
        out.writeInt(english);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.name = in.readUTF();
        this.math = in.readInt();
        this.english = in.readInt();
    }

}
