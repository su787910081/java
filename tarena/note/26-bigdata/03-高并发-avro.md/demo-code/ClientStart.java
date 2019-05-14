import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import avro.service.CalcService;

public class ClientStart {

    public static void main(String[] args) throws Exception {
        // 启动Netty 的客户端
        NettyTransceiver transceiver = new NettyTransceiver(
                new InetSocketAddress("localhost", 8090));
        
        // 利用它给服务器发起请求
        // 返回值代表指定接口的代理对象，代理对象在底层进行数据 的解析和传输
        CalcService proxy = SpecificRequestor.getClient(CalcService.class, transceiver);
        int result = proxy.add(3, 5);
        System.out.println(result);
    }
}
