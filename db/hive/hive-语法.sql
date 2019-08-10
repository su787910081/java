



-- 添加一个单独的自增序号列，但是必须要有分区(PARTITION BY)
ROW_NUMBER OVER(PARTITION BY xx)
    按指定分组后，然后添加一个单独的自增序号列(如下面的 rn 列)
        SELECT ROW_NUMBER() OVER(PARTITION BY name) rn, * FROM person;
    指定列分组且按age 排序排序，然后添加一个单独的自增序号列(如下面的 rn 列)
        SELECT ROW_NUMBER() OVER(PARTITION BY name ORDER BY age) rn, * FROM person;

-- 查看表详细结构
DESC FORMATTED tableName;

-- 详细建表示例。
    CREATE TABLE psn(
        id INT,
        name STRING,
        likes ARRAY<STRING>,
        address map<STRING, STRING>
    ) ROW FORMAT DELIMITED
        -- 字段之间分隔符
        FIELDS TERMINATED BY ','
        -- 集合元素之间分隔符
        COLLECTION ITEMS TERMINATED BY '-'
        -- map k-v 之间分隔符
        MAP KEYS TERMINATED BY ':'
    -- 存储文件格式
    STORED AS TEXTFILE;

-- 创建一张新表，数据和内容都与psn8 相同
    CREATE TABLE psn8 AS SELECT * FROM psn3;
-- 创建一张新表(空表)，与psn3 表的结构相同
    CREATE TABLE psn9 LIKE psn3;

-- 下面的这个是对某个分区中没有这个目录的时候使用。
-- 它只是创建了这个目录，在这个目录下面是还没有任何数据的。
-- 这个数据会在后期添加进来
ALTER TABLE psn6 ADD PARTITION(age=30, sex='boy');
-- 删除分区也是一样的，只是将分区对应的值的目录删除了
-- 删除分区时，可以只指定一个分区
ALTER TABLE psn6 DROP PARTITION(sex='boy');
-- 如果同时创建目录也有数据的情况下，都是使用 LOAD .... 的方式
LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)]



















