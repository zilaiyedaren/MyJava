package org.myself.mobile.web.http.httphessian;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-2
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public class HessianTest {
    //模拟客户端请求
    public static void main(String[] args){
        String url = "http://10.200.105.186:8022/mobile_web/getHessian.do";
        HessianProxyFactory hessianProxyFactory=new HessianProxyFactory();
        HessianInterface hessianInterface=null;
        WeatherInfo weatherInfo=new WeatherInfo();
        try {
            hessianInterface = (HessianInterface) hessianProxyFactory.create(HessianInterface.class, url);
            weatherInfo=hessianInterface.request();
        } catch (MalformedURLException e) {
            System.out.println("occur exception: " + e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(weatherInfo.getCity());
        System.out.println(hessianInterface.request().getPtime());
    }
}
