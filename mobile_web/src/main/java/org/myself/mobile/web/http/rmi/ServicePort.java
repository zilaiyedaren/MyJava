package org.myself.mobile.web.http.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-6
 * Time: 下午6:29
 * To change this template use File | Settings | File Templates.
 */
/*
建立服务器端，在服务器端注册RMI通讯端口与通讯路径，然后通讯javac命令编译文件，通过java -server 命令注册服务。
如果将项目建立于D://RMI/RemotingService文件夹上时，则先输入D://RMI /RemotingService/src>javac rmi/remotingservice/Program.java获取Program.class
然后输入D://RMI/RemotingService /src>java rmi/remotingservice/Program启动服务。
 */
//先启动服务端，然后再启动客户端
public class ServicePort {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            PersonService personService=new PersonServiceImpl();
            //注册通讯端口
            LocateRegistry.createRegistry(8022);
            //注册通讯路径
            Naming.rebind("rmi://10.200.105.186:8022/PersonService", personService);
            System.out.println("Service Start!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
