package com.xhc.test.dubbo.client.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xhc.test.dubbo.server.server.SayHelloToClient;

public class ServerInvoker {

    public String sayHello() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"applicationConsumer.xml"});
        context.start();
        
        SayHelloToClient demoServer = (SayHelloToClient)context.getBean("demoServer");
        return demoServer.sayHello("hello world. good to see you.");
    }
}
