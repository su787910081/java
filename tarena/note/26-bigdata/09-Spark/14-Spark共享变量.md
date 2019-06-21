

# 共享变量
- ## 概述
    - > Spark程序的大部分操作都是RDD操作，通过传入函数给RDD操作函数来计算。
    - > 这些函数在不同的节点上并发执行，但每个内部的变量有不同的作用域，不能相互访问，所以有时会不太方便
    - > Spark提供了两类共享变量供编程使用——广播变量和计数器。


# 广播变量
- ## 介绍
    - > 这是一个**只读对象**，在所有节点上都有一份缓存
- ## 使用
    - > `SparkContext.broadcast()`
    - > `val broadcastVar = sc.broadcast(Array(1, 2, 3))`
    - > `broadcastVar.value`
- ## 应用场景
    - > 可以解决 join 的数据倾斜问题
        > - 把小一股的数据广播出去。
        > - 这样所有的大表都能够得到这个小表的数据了。


# 计数器
- ## 介绍
    - > 计数器只能增加，是共享变量，用于计数或求和。
- ## 使用
    - > 计数器变量的创建方法是`SparkContext.accumulator(v, name)`，其中v是初始值，name是名称。
    - > `scala> val accum = sc.accumulator(0, "My Accumulator")`
    - > `scala> sc.parallelize(Array(1, 2, 3, 4)).foreach(x => accum += x)`
    - > `scala> accum.value`







