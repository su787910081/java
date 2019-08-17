
-- 官方地址
      https://blogs.oracle.com/sql/how-to-create-alter-and-drop-tables-in-sql
    
-- Create Table
    create table <table_name> (
        <column1> <data type>,
        <column2> <data type>,
        <column3> <data type>,
        ...
    );

    -- e.g.
    create table toys (
        toy_name varchar2(10),
        weight   number,
        colour   varchar2(10)
    );

    -- 根据SELECT 语句来创建表，创建出来的表拥有与查询语句相同的行和列的数据表。
        create table toys_clone as select * from toys;

-- alter table add column
    alter table toys add ( price number );

-- alter table drop column
    -- 删除一个列字段是比较费时的操作，官方不建议这样做。有要是在存储上十亿条数据时
        alter table toys drop ( weight );
    -- 可以标记该字段为不使用的操作
        alter table toys set unused column weight;



























