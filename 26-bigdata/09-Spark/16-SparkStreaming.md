
# SparkStreaming 介绍
## 概述
- ### 见图示
    ![](./img/SparkStreaming.png)
    ![](./img/SparkStreaming处理流程.png)

## 窗口
- ### 场景
    - > 假设：window length=3Min，sliding interval=1Min，batch interval=1Min
        > - 这个假设的主义就是：每隔1分钟，将当前最后3分钟的数据生成一个windowed DStream（如果有多个RDD，则合并他们）










