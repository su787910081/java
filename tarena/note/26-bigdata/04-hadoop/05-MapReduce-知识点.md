

- ## Mapper 组件
    - > `org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, IntWritable>`
    - > 继承自`Mapper` 模板类
    - > Mapper 组件的作用是定义每一个MapTask具体要怎么处理数据
    - > Mapper 计算的输入依据是由实现抽象类`InputFormat` 的抽象方法来决定的
        > - `org.apache.hadoop.mapreduce.InputFormat<K, V>`
        > - `getSplits` 抽象方法决定了mapper 的分片机制
        > - `createRecordReader` 抽象方法
    - > `InputFormat` 的实现类将输出分片格式，以两个参数的形式作为mapper 组件模板的前两个参数
        > - `Mapper` 组件模板前两个参数，由`ImputFormat` 的抽象方法的实现决定。
    - > `Mapper` 组件模板的后两个参数指定mapper 的输出，这个输出将交给Reducer 处理

- ## Reducer 组件
    - > `org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable>`
    - > Reducer 组件用于接收Mapper组件的输出
    - > reduce的输入key,需要和mapper的输出key类型一致
    - > reduce的输入value，需要和mapper的输出value类型一致
    - > reduce收到map的输出，会按相同的key做聚合，形成:key Iterable 形式然后通过reduce方法进行传递
    - > reduce方法中的Iterable是一次性的，即遍历一次之后，再遍历，里面就没有数据了。所以，在某些业务场景，会涉及到多次操作此迭代器。
        > - 处理的方法是：①先创建一个List  ②把Iterable装到List ③多次去使用List即可

- ## 序列化(Writable)
    - > 在MapReduce中，要求传输的数据能够被序列化
    - > 在Hadoop中，序列化机制默认使用的是AVRO
    - > Hadoop对AVRO的序列化机制进行了封装，提供了更简单的序列化方式
        > - 一个对象如果需要被序列化，那么这个对象对应的类需要实现Writable接口，然后重写其中的序列化方法和反序列化方法即可
        > - 要求被序列化的属性的值不能为null


- ## 分区  Partitioner
    - > 分区操作是shuffle操作中的一个重要过程，作用就是将map的结果按照规则分发到不同reduce中进行处理，从而按照分区得到多个输出结果。
    - > HashPartitioner是MapReduce的默认partitioner。
        > - 默认只有1个分区，也只有1个ReduceTask，所以只会产生一个结果文件
    - > 如果指定了多个分区，每一个分区要对应一个ReduceTask，每一个ReduceTask都会产生一个结果文件
    - > 分区默认是从0开始的，依次往上递增
    - > 如果需要自定义分区，那么需要写一个类继承Partitioner，然后重写其中的方法

- ## 排序(WritableComparable)
    - > Map执行过后，在数据进入reduce操作之前，数据将会按照输出的Key进行排序
    - > 要排序的对象对应类实现`WritableComparable`接口
        > - <mark>如果比较的结果一致，则会将相同的结果舍弃，所以一般情况下，我们不会将比较结果返回0。</mark>

- ## 合并(Combiner)
    - > Combiner 的输入是Mapper 的输出
        > - 只要我们指定了Combiner 那么MapTask 输出的结果就会执行Combiner
    - > 在Shuffle 阶段只有Spill 文件的数量达到3 个时才会Combiner

- ## 自定义InputFormat (mapper 的输入格式)
    - > InputFormat 的输出直接连接了Mapper 的输入
    - > Mapper 的默认InputFormat 是`TextInputFormat`
    - ### 概述
        - > MapReduce开始阶段阶段，InputFormat类用来产生InputSplit，并把基于RecordReader它切分成record（即KEYIN-VALUEIN），形成Mapper的输入。
        - > Hadoop本身提供了若干内置的InputFormat，其中如果不明确指定默认使用TextInputFormat。
    - ### 常见子类
        - > 因为MapReduce处理的数据通常是文件，所以考虑到切片相对比较麻烦，所以自定义输入格式的时候往往是写一个类去继承FileInputFormat
	    - > TextInputFormat：作为默认的文件输入格式，用于读取纯文本文件，文件被分为一系列以LF(Line-Feed 换行)或者CR(Carriage-Return 回车)结束的行，key是每一行的数据位置偏移量，是LongWritable类型的，value是每一行的内容，为Text类型
	    - > KeyValueTextInputFormat：同样用于读取文本文件，如果行被分隔符（默认是\t）分割为两部分，第一部分为key，剩下的部分为value；如果没有分隔符，整行作为 key，value为空
	    - >  SequenceFileInputFormat：用于读取sequence file。sequence file是Hadoop用于存储数据自定义格式的binary文件，这个文件中以KV结构来存储数据。它有两个子类：
		    > - SequenceFileAsBinaryInputFormat，将 key和value以BytesWritable的类型读出
		    > - SequenceFileAsTextInputFormat，将key和value以Text类型读出。
	    - > SequenceFileInputFilter：根据filter从sequence文件中取得部分满足条件的数据，通过 setFilterClass指定Filter，内置了三种 Filter，RegexFilter取key值满足指定的正则表达式的记录；PercentFilter通过指定参数f，取记录行数%f==0的记录；MD5Filter通过指定参数f，取MD5(key)%f==0的记录。
	    - > NLineInputFormat：0.18.x新加入，可以将文件以行为单位进行split，将文件的每一行对应一个mapper。得到的key是每一行的位置偏移量（LongWritable类型），value是每一行的内容，Text类型。适合于行数不多但是每一行的字段较多的场景
	    - > CompositeInputFormat：用于多个数据源的join
	    - > DBInputFormat：把数据库表数据读入到HDFS



- ## 指定reduce 的输出格式(OutputFormat)

- ## 分组(了解)
    - > 在reduce阶段进行，对mapper发送过来的数据会进行分组的操作，这个过程称为Grouping
    - > 默认情况下会将键相同的内容作为一组
    - > 可以通过`job.setGroupingComparatorClass(MyGroupingComparator.class);`方法自己指定Grouping规则
    - > 自定义分组需要继承指定类，并重写指定的方法
        > - 继承类：`public class WCComparator extends WritableComparator`
        > - 重写方法 `public int compare(byte[] b1, int begin1, int len1, byte[] b2, int begin2, int len2)`




