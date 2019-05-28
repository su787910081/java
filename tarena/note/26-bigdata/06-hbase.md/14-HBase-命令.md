


- ## HBase 命令
    - > `list`
        > - 查看当前有哪些表
    - > `create 'tabl1','cf1','cf2'`
        > - 创建表
    - > `put 'tabl1', 'row1','cf1:name','Rose'`
        > - 写数据
    - > `put 'tabl1','row1','cf1:age','35'`
    - > `scan 'tabl1'`
        > - 全表扫描
    - > `scan 'tabl1',{COLUMNS=>['cf1']}`
        > - 扫描全表中的指定列族
    - > `scan 'tabl1', {COLUMNS=>['cf1:name']}`
        > - 只扫描一列
    - > `scan 'tabl1', {COLUMNS=>['cf1:name','cf2:salary']}`
        > - 全表扫描两列
    - > `scan 'tabl1',{RAW=>true,VERSIONS=>3}`
        > - 查询最近的(最多)3个版本的历史数据
    - > `put 'tabl1', 'row1','cf2:gender', 'man'`
    - > `put 'tabl1', 'row1','cf2:salary','15000'`
    - > `get 'tabl1', 'row1'`
    - > `get 'tabl1', 'row1', 'cf1'`
    - > `get 'tabl1', 'row1', 'cf1', 'cf2'`
    - > `get 'tabl1', 'row1', 'cf1:name'`
    - > `eleteall 'tabl1','row1'`
        > - 根据行键删除整行数据
    - > `drop 'tabl1'`
        > - `disable 'tabl1'`
        > - 删除表之前要先禁用此用
