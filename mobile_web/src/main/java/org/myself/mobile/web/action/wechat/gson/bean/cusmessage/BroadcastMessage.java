package org.myself.mobile.web.action.wechat.gson.bean.cusmessage;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 14-1-8
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
public class BroadcastMessage {
    private String msgtype;

    private String touser;

    private MessNews news;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public MessNews getNews() {
        return news;
    }

    public void setNews(MessNews news) {
        this.news = news;
    }
}
