package org.myself.web.spring.utils.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-26
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public class MyHostnameVerifier implements HostnameVerifier{
    @Override
    public boolean verify(String hostname, SSLSession session) {
//        if("localhost".equals(hostname)){
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }
}
