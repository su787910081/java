

- # hive 内置函数
concat 可以操作int 类型

concat_ws 只能操作字符串
    select concat_ws('.', 'www', 'baidu','com');
    select concat_ws(',', id, name) from book;

repeat 将一个字符串重复N次
    repeat("abcd", 2);
    repeat(name, 2) from tablename;


explode 将一行切分成多行
    select explode(split("ab,cd,ef", ","));

exgexp_extract  按正则分组，每一个'()' 是一组，如果指定返回0 的话，总是返回整个字符串
    select regexp_extract("foothebar", "(foo)(.*)(bar)", 1);


