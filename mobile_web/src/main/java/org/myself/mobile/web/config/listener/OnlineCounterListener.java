package org.myself.mobile.web.config.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-11
 * Time: 下午6:48
 * To change this template use File | Settings | File Templates.
 */
public class OnlineCounterListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent hse) {
        OnlineCounter.raise();
    }
    public void sessionDestroyed(HttpSessionEvent hse){
        OnlineCounter.reduce();
    }

}
