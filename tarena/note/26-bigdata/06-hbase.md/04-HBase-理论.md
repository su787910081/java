

- # HBase 物理存储
    - ## HTable 表
        - > 一个HTable 表按行键化分出多个HRegion 
    - ### HRegion
        - > HRegion可以动态扩展并且HBase保证HRegion的负载均衡
        - > 一个HRegion 的每一个列族由一个HStore 存储
        - > 每个HRegion的大小可以是1～20GB。这个大小由`hbase.hregion.max.filesize`指定，默认为10GB
            > - 这个配置(`hbase.hregion.max.filesize`)在文件`hbase-site.xml` 中，单位是字节
            > - 当达到10GB 时，HMaster 将自动将其拆分并做转移
            > - HRegion的拆分和转移是由HBase（HMaster）自动完成的，用户感知不到
        - > HRegion是Hbase中分布式存储和负载均衡的最小单元
        - > HRegion虽然是分布式存储的最小单元，但并不是存储的最小单元
    - #### HStore(HFile)
        - > <mark>HRegion是分布式的存储最小单位，StoreFile(Hfile)是存储最小单位</mark>
        - > 每个HStore保存一个列族(columns family)
        - > 每一个HStroe 由一个MemStore 和0 个或者多个HStoreFile(HFile) 组成
    - ##### MemStore (写缓存)
        - > 默认是128M
    - ##### HStoreFile(HFile)
        - > StoreFile以HFile格式保存在HDFS上

- # HRegionServer
    - > 一个HRegionServer 中可能有多个HRegion存储单元
    - > 一般情况下HRegionServer 节点跟DataNode 节点放在一台物理计算机上(数据本地化策略)


- # HMaster 节点
    - > 管理HRegionServer, 实现其负载均衡
    - > 管理和分配HRegion，比如在HRegion split时分配新的HRegion；在HRegionServer退出时迁移其内的HRegion到其他HRegionServer上。
    - > 实现DDL操作（Data Definition Language，namespace和table的增删改，column familiy的增删改等）。
    - > 管理namespace和table的元数据（实际存储在HDFS上）。
    - > 权限控制（ACL）。


- # HBase 写流程


- # HBase 读流程






