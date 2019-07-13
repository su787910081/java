作业1：处理word.txt文件，统计每个单词的出现的频次
利用hive的hql来处理，最后的结果：
hello 5
hive 2
……

处理流程：①将word.txt文件上传到 /word 目录下
②通过hive建立外部表
③通过hql语句统计单词频次
select split(line, ' ') from wordCount;
select explode(split(line, ' ')) from wordCount;
select w.simp, count(w.simp) from (select explode(split(line, ' ')) simp from wordCount) w group by w.simp;


作业2：处理replace.txt文件，通过hive做数据清洗
将所有的其他字符清洗掉
可以用：regexp_replace 函数
select regexp_replace(字符串或列,正则表达式，替换的字符串)

提示:符号需要转义 [@$]

create external table replace01(info string) location '/homework/replace/';
select regexp_replace(info, "[\*@%#&]", '') from replace01;
select regexp_replace(regexp_replace(info, "[*@%#&]", ''), '[.][.]', '.') from replace01;

作业3：处理extract.txt文件，用hive做数据的提取
可以用：regexp_extract
提取的每行的域名：
www.baidu.com
www.sina.com
baidu.com
select regexp_extract(info, 'http://(.*)(:)(.*)(:)(.*)', 1) from extract;

作业4：
处理order.txt和product.txt，完成两个表的join操作。


