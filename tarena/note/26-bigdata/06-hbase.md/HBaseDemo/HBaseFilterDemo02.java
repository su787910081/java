package cn.tedu.hbase.suyh;

import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.PrefixFilter;

import org.junit.Test;

public class HBaseFilterDemo02 {
    public static final String ZK_CLUSTER_VM = "HBase01:2181,HBase02:2181,HBase03:2181";
    public static final String ZK_CLUSTER_TARENA = "tarena01:2181,tarena02:2181,tarena03:2181";
    public static final String ZK_CURRENT = ZK_CLUSTER_TARENA;

    // 正则过滤器
    @Test
    public void regexFilter() throws Exception {
        // 获取对HBase 的环境变量参数对象
        Configuration conf = HBaseConfiguration.create();

        // 设置Zookeeper 的连接地址
        conf.set("hbase.zookeeper.quorum", ZK_CURRENT);

        HTable table = new HTable(conf, "tab2");

        // --在使用过滤器，可以配合扫描对象的扫描范围来使用
        Scan scan = new Scan();
        // scan.setStartRow("row30".getBytes());
        // scan.setStopRow("row60".getBytes());

        // --正则匹配过滤器，下例是匹配行键中含3的行键。
        // Filter filter=new RowFilter(CompareOp.EQUAL,
        // new RegexStringComparator("^.*3.*$"));

        // --行键比较过滤器，比较规则：①等于 ②大于 ③ 小于 ④大于等于 ⑤小于等于
        // Filter filter=new RowFilter(CompareOp.LESS_OR_EQUAL,
        // new BinaryComparator("row90".getBytes()));

        // --行键前缀过滤器
        // Filter filter=new PrefixFilter("row3".getBytes());

        // --列值过滤器，匹配指定符合列值的所有行数据
        Filter filter = new SingleColumnValueFilter("cf1".getBytes(),
                "name".getBytes(), CompareOp.EQUAL, "rose".getBytes());

        scan.setFilter(filter);
        ResultScanner result = table.getScanner(scan);
        Iterator<Result> it = result.iterator();

        while (it.hasNext()) {
            Result r = it.next();
            byte[] name = r.getValue("cf1".getBytes(), "name".getBytes());
            byte[] age = r.getValue("cf1".getBytes(), "age".getBytes());
            System.out.println(new String(name) + ":" + new String(age));
        }
        table.close();
    }

}
