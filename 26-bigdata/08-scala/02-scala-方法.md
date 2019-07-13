

- > 手册地址：https://www.scala-lang.org/api/2.10.4/index.html#package


| 方法 | 描述 |
| :-- | :-- |
|  take             |  取前N 项元素       |
|  takeRight        |  取右N 项元素      |
|  drop             |  删除前N 项元素，返回剩余元素      |
|  dropRight        |  删除右N 项元素，返回剩余元素      |
|  head             |  取出第一个元素    |
|  last             |  取出最后一个元素      |
|  mkString         |  生成一个字符串，并提供一个分隔符      |
|  intersect        |  交集      |
|  unio             |  并集      |
|  diff             |  差集      |
|  exists           |  是否存在指定规则的元素    |
|  count            |  满足规则的元素的个数      |
|  filter           |  过滤指定条件的元素    |
|  sortBy           |  按指定规则排序    |
|  map              |  将每一个元素按规则映射成另外一种元素类型，集合的类型是不会改变的。    |
|  max              |  最大的一个元素    |
|  min              |  最小的一个元素    |
|  sum              |  所有元素求和      |
|  reduce           |  每前两个相邻元素按指定规则进行处理，处理的结果与后一个元素继续按此规则处理，返回最终结果      |
|  reverse          |  元素反转      |
|  distinct         |  去重      |
|  flatMap          |  扁平化map，按指定规则进行映射，映射的结果元素将会是一个单元素集合 List\[String\] |
|  groupBy          |  按指定规则做聚合，最后将结果返回到一个map映射里。   |
|  platMap          |  它所需要的是一个函数，这个函数只要返回一个集合. 然后，scala 会将它认为是一个二维数组，将这个二维数组直接转换成一个一维数组。 |
| concat            |  将两个数组拼接成一个数组  |
| quickSort         | val val3=Array\[Int\](1,2,3,4); val val4=scala.util.Sorting.quickSort(val3) |
