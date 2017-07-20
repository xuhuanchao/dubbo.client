package com.xhc.test.dubo.self.customer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.Properties;

import com.xhc.test.dubbo.self.service.IStudy;

public class Customer {

    
    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        p.load(Customer.class.getResourceAsStream("./service.properties"));
        
        
        
        Class clazz = Class.forName(p.getProperty("study"));
        IStudy StudyImpl = (IStudy) Proxy.newProxyInstance(clazz.getClassLoader(),  
               new Class[]{clazz}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("127.0.0.1", 8888);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        
                        //传输数据内容：1.serviceName服务名 2.方法名 3.方法参数类型 4.方法参数值
                        oos.writeUTF(p.getProperty("study"));
                        oos.writeUTF(method.getName());
                        Class[] paramTypes = new Class[]{String.class};
                        oos.writeObject(paramTypes);
                        Object[] params = new Object[]{"chiken"};
                        oos.writeObject(args);
                        
                        oos.flush();
                        
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        Object result = ois.readObject();
                        System.out.println("Provider return : "+result);
                        
                        ois.close();
                        oos.close();
                        return result;
                    }
                });
            StudyImpl.study("sport");
        
    }
}
