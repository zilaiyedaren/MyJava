package org.myself.mobile.web.http.rmi;

import java.rmi.Naming;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-6
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
//建立客户端进行测试，注意客户调用的RMI路径必须服务器配置一致
public class ClientPort {
    public static void main(String[] args){
        try{
            //调用远程对象，注意RMI路径与接口必须与服务器配置一致
            PersonService personService=(PersonService) Naming.lookup("rmi://10.200.105.186:8022/PersonService");
            List<Person> personList=personService.GetList();
            for(Person person:personList){
                System.out.println("ID:"+person.getId()+" Age:"+person.getAge()+" Name:"+person.getName());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
