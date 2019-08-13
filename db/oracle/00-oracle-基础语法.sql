


-- 建表
    -- 建一张表，但是格式与另一张表一致
        -- WHERE 条件为真，则表的格式与数据都与源表一样
        -- WHERE 条件为假，则只要源表的格式，不要源表的数据
            CREATE TABLE BRIEF_FND_FLEX_VALUES_VL AS SELECT * FROM DBI_FND_FLEX_VALUES_VL WHERE 1=0;

-- 添加注释
    -- 添加表注释
        COMMENT ON TABLE  DBI_BALANCES  is 'OF余额导入接口表';
    -- 添加字段注释
        COMMENT ON COLUMN DBI_FND_FLEX_VALUES_VL.BIZ_DATE is '业务日期';



