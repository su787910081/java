package cn.tedu.ZK;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.junit.Before;
import org.junit.Test;

public class ZooKeeperDemo02 {
    private ZooKeeper zk;

    @Before
    public void ocnnect() throws Exception {
        final CountDownLatch cdl = new CountDownLatch(1);

        zk = new ZooKeeper("192.168.220.130:2181", 5000, new Watcher() {

            public void process(WatchedEvent event) {

                if (event.getState() == KeeperState.SyncConnected) {
                    System.out.println("连接成功~~~");
                }
                cdl.countDown();
            }
        });

        cdl.await();
    }

    @Test
    public void dataChanged() throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);

        // 获取到的是改变之前的数据
        byte[] data = zk.getData("/video", new Watcher() {

            public void process(WatchedEvent event) {
                if (event.getType() == EventType.NodeDataChanged) {
                    System.out.println("节点数据发生变化~~~");
                }

                cdl.countDown();
            }
        }, null);

        cdl.await();

        System.out.println(new String(data));
    }

    // 监控子节点个数是否发生了变化
    @Test
    public void childChanged() throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);

        zk.getChildren("/video", new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == EventType.NodeChildrenChanged) {
                    System.out.println("子节点数量发生了变化 ");
                }

                cdl.countDown();
            }
        });

        cdl.await();
    }
    
    // 监控节点的增删变化
    @Test
    public void nodeChanged() throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);

        zk.exists("/log", new Watcher() {
            
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == EventType.NodeCreated) {
                    System.out.println("新添了log 结点");
                } else if (event.getType() == EventType.NodeDeleted) {
                    System.out.println("删除了log 节点");
                }
                
                cdl.countDown();
            }
        });
        
        cdl.await();
    }
}
