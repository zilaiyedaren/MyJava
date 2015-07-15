package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-8
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public class ExpressInfo {
    private String expressTime;

    private String expressContext;

    private String  expressFtime;

    public String getExpressTime() {
        return expressTime;
    }

    public void setExpressTime(String expressTime) {
        this.expressTime = expressTime;
    }

    public String getExpressContext() {
        return expressContext;
    }

    public void setExpressContext(String expressContext) {
        this.expressContext = expressContext;
    }

    public String getExpressFtime() {
        return expressFtime;
    }

    public void setExpressFtime(String expressFtime) {
        this.expressFtime = expressFtime;
    }
}
