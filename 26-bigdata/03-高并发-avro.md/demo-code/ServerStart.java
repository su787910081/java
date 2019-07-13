package cn.tedu;

import java.net.InetSocketAddress;

import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.specific.SpecificResponder;

import avro.service.CalcService;
import avro.service.CalcServiceImpl;

public class ServerStart {
    public static void main(String[] args) {
        NettyServer server = new NettyServer(
                new SpecificResponder(CalcService.class, new CalcServiceImpl()), 
                new InetSocketAddress(8090));
    }
}
