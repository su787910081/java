

- ## 题1
    - ### 源文件
        - > 源文件`item01`:
            >>       1 {A:京东,裙子:1}
            >>       2 {A:京东,裙子:2}
            >>       3 {B:成都,鞋子:1}
            >>       4 {A:天猫,裙子:5}
            >>       6 {B:北京,鞋子:2}
            >>       7 {A:天猫,鞋子:2}
            >>       8 {B:北京,鞋子:3}
            >>       9 {B:北京,鞋子:4}
            >>       10 {B:成都,鞋子:2}
    - ### 统计出线上渠道的子渠道所有商品的sum合计
        - > 创建外部表
            >> - `CREATE EXTERNAL TABLE item(id INT, info STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' LOCATION '/hiveTest01';`
