package avrodemo;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import avro.pojo.Person;

public class PersonTest {

    @Test
    public void testPerson() {
        Person p1 = new Person();
        p1.setAge(19);
        p1.setName("abc");
        p1.setGender(true);

        Person p3 = Person.newBuilder(p1).setAge(17).build();
        System.out.println(p3);
    }

    @Test
    public void testSerial() throws Exception {
        Person p1 = new Person();
        p1.setAge(19);
        p1.setName("abc");
        p1.setGender(true);
        
        Person p2 = Person.newBuilder(p1).setAge(17).build();
        
        // 创建序列化流
        DatumWriter<Person> dw = new SpecificDatumWriter<>(Person.class);
        // 将序死化的数据写到文件中
        DataFileWriter<Person> df = new DataFileWriter<>(dw);
        // 指定写出的文件
        df.create(Person.SCHEMA$, new File("a.txt"));
        // 序死化对象
        df.append(p1);
        df.append(p2);
        
        df.close();
    }
    
    @Test
    public void testDeserial() throws Exception {
        DatumReader<Person> dw = new SpecificDatumReader<>(Person.class);
        // 从文件中读数据 
        DataFileReader<Person> df 
        = new DataFileReader<>(new File("a.txt"), dw);
        
        // 读对象
        // Person p1 = df.next();
        // System.out.println(p1);
        // Person p2 = df.next();
        // System.out.println(p2);
        
        // 提供了迭代遍历
        while (df.hasNext()) {
            System.out.println(df.next());
        }
        
        df.close();
    }
}
