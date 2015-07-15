package org.myself.mobile.web.http.httphessian;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-2
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
//针对hessian需要在客户端定义一个接口，并实现
public interface HessianInterface{
    public WeatherInfo request();
}
