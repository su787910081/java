


# SparkConf类
- ## 功能
    - > conf.setMaster("local") // 本地
    - > conf.setMaster("spark://hadoop:7077")   // 网络服务器
    >       /**
    >        * Configuration for a Spark application. Used to set various Spark parameters as key-value pairs.
    >        *
    >        * Most of the time, you would create a SparkConf object with `new SparkConf()`, which will load
    >        * values from any `spark.*` Java system properties set in your application as well. In this case,
    >        * parameters you set directly on the `SparkConf` object take priority over system properties.
    >        *
    >        * For unit tests, you can also call `new SparkConf(false)` to skip loading external settings and
    >        * get the same configuration no matter what the system properties are.
    >        *
    >        * All setter methods in this class support chaining. For example, you can write
    >        * `new SparkConf().setMaster("local").setAppName("My app")`.
    >        *
    >        * @param loadDefaults whether to also load values from Java system properties
    >        *
    >        * @note Once a SparkConf object is passed to Spark, it is cloned and can no longer be modified
    >        * by the user. Spark does not support modifying the configuration at runtime.
    >        */
- ## 部分源码
    >       // 用来存储key-value的配置信息
    >       private val settings = new ConcurrentHashMap[String, String]()
    >       // 默认会加载“spark.*”格式的配置信息
    >       if (loadDefaults) {
    >         // Load any spark.* system properties
    >         for ((key, value) <- Utils.getSystemProperties if key.startsWith("spark.")) {
    >           set(key, value)
    >         }
    >       }
    >       /** Set a configuration variable. */
    >       def set(key: String, value: String): SparkConf = {
    >         if (key == null) {
    >           throw new NullPointerException("null key")
    >         }
    >         if (value == null) {
    >           throw new NullPointerException("null value for " + key)
    >         }
    >         logDeprecationWarning(key)
    >         settings.put(key, value)
    >         // 每次进行设置后都会返回SparkConf自身，所以可以进行链式的调用
    >         this
    >       }

# SparkContext 类
- ## SparkContext最主要的作用:①初始化SparkEnv对象 ②初始化并启动三个调度模块DAG，Task,Backend，此外，会建立各个工作节点的心跳机制，用于检测和监控
    >       // 是否允许存在多个SparkContext，默认是false
    >       // If true, log warnings instead of throwing exceptions when multiple SparkContexts are active
    >       private val allowMultipleContexts: Boolean =
    >         config.getBoolean("spark.driver.allowMultipleContexts", false)


# 内存管理器 MemoryManager
## 内存模块
- ### 说明
    - > 每个Executor进程都有一个MemoryManager。
- ### storageMemory（存储内存）
    - > 用来缓存rdd，unroll partition，direct task result、广播变量等
- ### executionMemory（执行内存）
    - > 用于shuffle、join、sort、aggregation 计算中的缓存
- ### 其他内存
    - > 除了这两者以外的内存都是预留给系统的。

## 两套管理方案
- ### StaticMemoryManager 静态管理(旧)
    - > 拥有的内存是独享的不可相互借用
        > - 所以在其中一方内存充足，但另一方内存不足但又不能借用的情况下会造成资源的浪费
- ### UnifiedMemoryManager 动态管理(新)
    - > 统一管理，初始状态是内存各占一半，相互之间可以借用
        > - 其中一方内存不足时可以向对方借用，对内存资源进行合理有效的利用，提高了整体资源的利用率。

## 参数配置
- ### 对应参数
    - > `spark.memory.useLegacyMode`
        > - 默认值：`UnifiedMemoryManager` 动态管理机制







