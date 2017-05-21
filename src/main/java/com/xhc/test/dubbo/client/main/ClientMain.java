package com.xhc.test.dubbo.client.main;

import com.xhc.test.dubbo.client.server.ServerInvoker;
import com.xhc.test.dubbo.server.server.*;

public class ClientMain {

    /**
     * 启动时 需要jvm参数：-Djava.net.preferIPv4Stack=true
     * @param args
     */
    public static void main(String[] args) {
        ServerInvoker  serverInvoker = new ServerInvoker();
        String result = serverInvoker.sayHello();
        System.out.println("server back message :" + result);
    }
}
