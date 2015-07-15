package org.myself.mobile.web.action.wechat.gson.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 14-1-8
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
public class Broadcast {
    private String msgtype;

    private String touser;

    private BroadcastContent text;

    public BroadcastContent getText() {
        return text;
    }

    public void setText(BroadcastContent text) {
        this.text = text;
    }

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
}
