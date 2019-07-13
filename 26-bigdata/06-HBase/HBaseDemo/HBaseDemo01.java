package cn.tedu.hbase.suyh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.junit.Test;

public class HBaseDemo01 {
    public static final String ZK_CLUSTER_VM = "HBase01:2181,HBase02:2181,HBase03:2181";
    public static final String ZK_CLUSTER_TARENA = "tarena01:2181,tarena02:2181,tarena03:2181";
    public static final String ZK_CURRENT = ZK_CLUSTER_TARENA;

    @Test
    public void cresateTable() throws Exception {
        // 获取对HBase 的环境变量参数对象
        Configuration conf = HBaseConfiguration.create();

        // 设置Zookeeper 的连接地址
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        // 获取HBase 的管理权限
        HBaseAdmin admin = new HBaseAdmin(conf);

        // 创建HBase 表对象，并指定表名
        // 创建表描述器
        HTableDescriptor table = new HTableDescriptor(
                TableName.valueOf("tab1"));

        // 创建HBase 表的列族对象
        HColumnDescriptor cf1 = new HColumnDescriptor("cf1");
        HColumnDescriptor cf2 = new HColumnDescriptor("cf2");
        // 指定某个列族在cell 上最多保留的历史 版本，默认就是3
        cf1.setMaxVersions(3);

        // 将表与列族进行绑定
        table.addFamily(cf1);
        table.addFamily(cf2);

        // 执行建表动作
        admin.createTable(table);

        // 关闭流
        admin.close();
    }

    @Test
    public void putData() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        // 获取对应的表对象
        HTable table = new HTable(conf, "tab1");
        // 指定行键来添加数据
        Put row = new Put("row1".getBytes());
        // 列族、列名、列数据
        row.add("cf1".getBytes(), "name".getBytes(), "Tom".getBytes());
        row.add("cf1".getBytes(), "age".getBytes(), "23".getBytes());
        row.add("cf1".getBytes(), "gender".getBytes(), "man".getBytes());

        // 执行插入
        table.put(row);

        // table 是HBase 提供的针对表进行操作的流
        table.close();
    }

    @Test
    public void put100Row() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        // 获取对应的表对象
        HTable table = new HTable(conf, "tab2");
        for (int i = 0; i < 100; i++) {
            String key = "row" + i;
            String value = i + "";

            // 指定行键来添加数据
            Put row = new Put(key.getBytes());

            // 列族、列名、列数据
            row.add("cf1".getBytes(), "number".getBytes(), value.getBytes());

            // 执行插入
            table.put(row);
        }

        // table 是HBase 提供的针对表进行操作的流
        table.close();
    }

    @Test
    public void getData() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        HTable table = new HTable(conf, "tab1");
        Get get = new Get("row1".getBytes()); // 参数：行键
        Result result = table.get(get); // 执行查询，并返回结果集
        byte[] name = result.getValue("cf1".getBytes(), "name".getBytes());
        byte[] age = result.getValue("cf1".getBytes(), "age".getBytes());
        System.out.println("HBaseDemo.getData(): " + new String(name) + ','
                + new String(age));

        table.close();
    }
    
    @Test
    public void scaa() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        HTable table = new HTable(conf, "tab2");
        
        // 扫描对象 默认起始位置为开始位置终止位置为全表
        // 扫描区间为左闭右开
        Scan s = new Scan();
        s.setStartRow("row1".getBytes());
        s.setStopRow("row2".getBytes());
        // 执行整表扫描
        ResultScanner result = table.getScanner(s);
        Iterator<Result> it = result.iterator();
        while (it.hasNext()) {
            Result r = it.next();
            byte[] number = r.getValue("cf1".getBytes(), "number".getBytes());
            System.out.println(new String(number));
        }

        result.close();
  
        table.close();
    }

    @Test
    public void deleteRow() throws Exception{
        Configuration conf=HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        HTable table=new HTable(conf,"tab1");
        
        Delete delete=new Delete("row1".getBytes());
        //--执行删除
        table.delete(delete);
        
        table.close();
        
    }
    
    @Test
    public void testDropTable() throws Exception, IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        // 删除表之前要先禁用表
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable("tab1".getBytes());
        admin.deleteTable("tab1".getBytes());
        admin.close();
    }

    @Test
    public void putmillion() throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        HTable table = new HTable(conf, "student");

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            List<Put> puts = new ArrayList<>();
            for (int j = 0; j < 10000; j++) {
                int num = i * 10000 + j;
                Put put = new Put(("stu" + num).getBytes());
                put.add("basic".getBytes(), "no".getBytes(),
                        ("No-" + num).getBytes());

                puts.add(put);
            }

            table.put(puts);
        }
        long end = System.currentTimeMillis();
        System.out.println("HBaseDemo.putmillion(): " + (end - begin));

        table.close();
    }


}
