package org.myself.mobile.web.action.wechat.gson.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 14-3-18
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
public class WxPayRequest {

    private String OpenId;
    private String AppId;
    private String IsSubscribe;
    private String ProductId;
    private String TimeStamp;
    private String NonceStr;
    private String AppSignature;
    private String SignMethod;

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String openId) {
        OpenId = openId;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getNonceStr() {
        return NonceStr;
    }

    public void setNonceStr(String nonceStr) {
        NonceStr = nonceStr;
    }

    public String getAppSignature() {
        return AppSignature;
    }

    public void setAppSignature(String appSignature) {
        AppSignature = appSignature;
    }

    public String getSignMethod() {
        return SignMethod;
    }

    public void setSignMethod(String signMethod) {
        SignMethod = signMethod;
    }

    public String getIsSubscribe() {
        return IsSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        IsSubscribe = isSubscribe;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
