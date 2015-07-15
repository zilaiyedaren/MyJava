package org.myself.mobile.web.http.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-6
 * Time: 下午6:23
 * To change this template use File | Settings | File Templates.
 */
//创建远程接口PersonService,注意远程接口需要继承Remote
public interface PersonService extends Remote {
        public List<Person> GetList() throws RemoteException;

}
