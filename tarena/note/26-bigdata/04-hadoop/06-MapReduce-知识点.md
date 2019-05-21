

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
        > - 如果比较的结果一致，则会将相同的结果舍弃，所以一般情况下，我们不会将比较结果返回0。

- ## 合并(Combiner)
    - > 每一个MapperTask可能会产生大量的输出，Combiner的作用就是在MapperTask端对输出先做一次合并，以减少传输到reducerTask的数据量
    - > Combiner是实现在Mapper端进行key的归并，Combiner具有类似本地的reduce功能
    - > 合并的目的是将Reducer的部分计算提前挪到Mapper端，从而降低Reducer的计算量

- ## 指定mapper 的输入格式(InputFormat)
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

- ##  Shuffle (洗牌)
    - ### map 阶段
        - > 当提交MR程序之后要对文件进行切片。
            > - 如果文件不可切(例如压缩文件)，此时将整个文件作为一个切片
            > - 如果文件可切，那么默认切片和切块是一样大的，但是如果(`最后剩余的数据/splitsize<1.1`)，则最后的数据整体作为一个切片来进行处理；
        - > 切片之后，每一个切片分配给一个MapTask。如果不指定，每一个MapTask的处理方式是对切片进行按行读取。如果文件不是字符类文件，那么需要指定输入格式
        - > 每读取一行，调用一次map方法，map方法处理完成之后会将结果以键值对形式写出
        - > MapTask调用map方法产生结果之后会先将结果写到缓冲区。每一个MapTask自带一个缓冲区，缓冲区是维系在内存中。等MapTask将当前切片处理完成之后会再一次性的给ReduceTask
        - > 数据在缓冲区中是分区（partition）并且排序(sort)的。如果指定了Combiner，在缓冲区中还会进行合并
        - > 缓冲区默认大小是100M。当缓冲区的使用达到阈值（0.8）之后，会认为缓冲区快要满了，此时将缓冲区中的数据临时写到本地的磁盘上，这个过程称之为溢写（Spill）
        - > 溢写之后，数据继续往缓冲区中来存放，在数据量比较大的情况下，可能会产生多次溢写，从而产生多个溢写文件
        - > 最后一次溢写之后，如果依然有数据存在缓冲区中，会将缓冲区中的数据冲刷到最后一个溢写文件中
        - > 如果产生了多个溢写文件，那么在数据处理完成之后，MapTask会将它产生的所有的溢写文件合并(merge)成一个文件，产生最后的结果文件 - merge不会减少数据量
        - > 在merge过程中，会对数据进行整体的分区排序
        - > 如果产生溢写文件>=3个，那么在merge过程中就会自动进行合并
        - ### 注意问题: 
            - > 溢写过程不一定，在处理完当前切片的数据之后，将缓冲区中的数据直接写到最后的结果文件中
            - > 切片的大小不能决定溢写次数
            - > 在默认情况下，溢写文件的大小不一定是80M
            - > 缓冲区本质上是环形的字节数组
            - > 环形缓冲区的好处在于能够避免重新寻址 - 重新找开头的地址
            - > 阈值的作用：为了防止往缓冲区写结果的时候产生阻塞
    - ### reduce 阶段
        - > ReduceTask的设置和分区相关，每一个分区对应一个ReduceTask
        - > ReduceTask启动fetch线程去MapTask上抓取当前ReduceTask所对应的分区的数据
        - > 将抓取来的数据进行merge，合并成一个结果文件
        - > 在merge过程中，对数据再次进行整体的排序
        - > 将相同的键所对应的值放入一个List中，然后利用这个List产生一个Iterable对象，交给Reduce来执行，这个过程称之为分组（group）
        - > 每一个键调用一次reduce方法，将reduce方法的结果写到HDFS上
        - ### 注意问题：
            - > fetch线程的数量默认为5
            - > fetch线程通过http请求来抓取数据
            - > merge因子默认为10，表示每10个小文件合并成一个大文件
            - > ReduceTask启动阈值为0.05，表示当5%的MapTask执行完成之后，ReduceTask就启动来开始抓取数据

- ## Shuffle调优
	- ### Map端：
		- > 调大缓冲区，实际开发中一般是将缓冲区设置为250~400M之间（建议）
		- > 调大阈值，可以减少Spill的次数，但同时增加线程的阻塞的概率（不建议）
		- > 增加Combine过程（建议）
		- > 将结果进行压缩，好处在于减少MapTask和ReduceTask之间传输的数据量，减少网络传输的时间，同时ReduceTask这一端还需要将数据解压
            > - 这种方案实际上是网络资源的取舍问题
	- ### Reduce端：
		- > 增多fetch线程的数量（建议）
		- > 增大merge因子
        - >减小ReduceTask的阈值（不建议）