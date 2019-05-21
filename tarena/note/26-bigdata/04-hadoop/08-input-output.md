

- ## 简介
	- >  控制着数据向Map的输入。默认情况下，会对文件进行切片并且进行按行读取
	- >  如果需要自定义输入格式，那么就需要写一个类继承InputFormat
	- >  InputFormat的功能：
	    > - 校验指定的文件是否存在
	    > - 对文件进行切片
	    > - 提供了对每一个切片进行读取的输入流
	- >  因为MapReduce处理的数据通常是文件，所以考虑到切片相对比较麻烦，所以自定义输入格式的时候往往是写一个类去继承FileInputFormat
	- >  MapReduce中，如果不指定，输入格式默认是TextInputFormat

- ## 常见输入格式
	1. TextInputFormat：作为默认的文件输入格式，用于读取纯文本文件，文件被分为一系列以LF(Line-Feed 换行)或者CR(Carriage-Return 回车)结束的行，key是每一行的数据位置偏移量，是LongWritable类型的，value是每一行的内容，为Text类型
	2. KeyValueTextInputFormat：同样用于读取文本文件，如果行被分隔符（默认是\t）分割为两部分，第一部分为key，剩下的部分为value；如果没有分隔符，整行作为 key，value为空
	3. SequenceFileInputFormat：用于读取sequence file。sequence file是Hadoop用于存储数据自定义格式的binary文件，这个文件中以KV结构来存储数据。它有两个子类：
		a. SequenceFileAsBinaryInputFormat，将 key和value以BytesWritable的类型读出
		b. SequenceFileAsTextInputFormat，将key和value以Text类型读出。
	4. SequenceFileInputFilter：根据filter从sequence文件中取得部分满足条件的数据，通过 setFilterClass指定Filter，内置了三种 Filter，RegexFilter取key值满足指定的正则表达式的记录；PercentFilter通过指定参数f，取记录行数%f==0的记录；MD5Filter通过指定参数f，取MD5(key)%f==0的记录。
	5. NLineInputFormat：0.18.x新加入，可以将文件以行为单位进行split，将文件的每一行对应一个mapper。得到的key是每一行的位置偏移量（LongWritable类型），value是每一行的内容，Text类型。适合于行数不多但是每一行的字段较多的场景
	6. CompositeInputFormat：用于多个数据源的join
	7. DBInputFormat：把数据库表数据读入到HDFS
