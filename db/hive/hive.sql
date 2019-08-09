

-- 添加一个单独的自增序号列，但是必须要有分区(PARTITION BY)
ROW_NUMBER OVER(PARTITION BY xx)
    按指定分组后，然后添加一个单独的自增序号列(如下面的 rn 列)
        SELECT ROW_NUMBER() OVER(PARTITION BY name) rn, * FROM person;
    指定列分组且按age 排序排序，然后添加一个单独的自增序号列(如下面的 rn 列)
        SELECT ROW_NUMBER() OVER(PARTITION BY name ORDER BY age) rn, * FROM person;


