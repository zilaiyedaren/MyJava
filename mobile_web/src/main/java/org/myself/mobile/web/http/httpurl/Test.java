package org.myself.mobile.web.http.httpurl;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-4
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args){
        try {
//            HttpUrl.get();//success
            HttpUrl.post();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
