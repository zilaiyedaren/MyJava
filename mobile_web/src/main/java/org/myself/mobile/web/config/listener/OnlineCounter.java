package org.myself.mobile.web.config.listener;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-11
 * Time: 下午6:45
 * To change this template use File | Settings | File Templates.
 */
public class OnlineCounter {
    private static long online =0;

    public static long getOnline(){
        return online;
    }
    public static void raise(){
        online++;
    }
    public static void reduce(){
        online--;
    }
}
