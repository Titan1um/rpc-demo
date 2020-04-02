package com.jun.demo;

import com.jun.demo.Interface.Server;
import com.jun.demo.Interface.Service;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {

    public static void main(String args[]) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(Service.class, ServiceImpl.class);
                    serviceServer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Service service = RpcClient.getRemoteProxyObj(Service.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.touchFish("Titan1um"));

        //need to know 这个类算是网络的调用，还是已经缓存到本地的
        //看代码可知，是远程调用
        try {
            System.out.println(service.getClass().getMethod("touchFish", String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
