package cn.tedu.ZK;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZooKeeperDemo01 {
    private ZooKeeper zk;
    
    @Before
    public void connect() throws Exception {
        
        final CountDownLatch cdl = new CountDownLatch(1);
        
        // String connectString, 服务器地址
        // int sessionTimeout, 超时时间，单位毫秒
        // Watcher watcher - 监控者，监控这个连接
        // 连接与监控是两个不同的线程，且非阻塞
        zk = new ZooKeeper("192.168.220.130:2181", 
                5000, new Watcher() {
                    
                    public void process(WatchedEvent event) {
                        // 利用这个监控可以确定连接是否建立
                        if (event.getState() == KeeperState.SyncConnected) {
                            System.out.println("连接成功~~~");
                        }
                        
                        cdl.countDown();
                    }
                });
        
        cdl.await();
    }
    
    @Test
    public void create() throws Exception {
        // String path, - 路径
        // byte data[], - 数据
        // List<ACL> acl, - 权限
        // CreateMode createMode - 指定节点类型
        // 返回一个实际 名称(在创建顺序节点时有用)
        String cpath = zk.create("/log", "日志服务器".getBytes(), 
                Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(cpath);
    }
    
    @Test
    public void delete() throws Exception {
        // String path, - 路径
        // int version - 数据版本(dataVersion)  -1: 忽略版本检查，强制删除
        // 删除节点时会比较数据版本是否一致，如果一致则删除，否则无效
        zk.delete("/log", 1);
        
//        zk.delete("/log", -1);
    }
    
    @Test
    public void setData() throws Exception {
        // 返回一个节点信息
        Stat s = zk.setData("/video", "视频服务器".getBytes(), -1);
        System.out.println(s);
    }
    
    @Test
    public void getData() throws Exception {
        // String path, 
        // Watcher watcher, - 监控者，表示将数据获取到之后要做什么操作
        // Stat stat - 节点信息，这个方法它会将节点信息放在这个对象里面
        byte[] data = zk.getData("/video", null, null);
        System.out.println(new String(data));
    }
    
    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        // 获取所有子节点为的路径集合
        List<String> children = zk.getChildren("/", null);
        System.out.println(children);
    }
    
    // 判断节点是否存在
    @Test
    public void exist() throws Exception {
        // 如果这个节点不存在，则返回null 
        Stat s = zk.exists("/log", null);
        System.out.println(s);
    }
}
