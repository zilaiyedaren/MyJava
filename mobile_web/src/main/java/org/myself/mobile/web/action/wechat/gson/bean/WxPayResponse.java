package org.myself.mobile.web.action.wechat.gson.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 14-3-18
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
public class WxPayResponse {

    private String AppId;
    private String Package;
    private String TimeStamp;
    private String NonceStr;
    private String AppSignature;
    private String RetCode;
    private String RetErrMsg;
    private String SignMethod;

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String aPackage) {
        Package = aPackage;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
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

    public String getRetCode() {
        return RetCode;
    }

    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    public String getRetErrMsg() {
        return RetErrMsg;
    }

    public void setRetErrMsg(String retErrMsg) {
        RetErrMsg = retErrMsg;
    }

    public String getSignMethod() {
        return SignMethod;
    }

    public void setSignMethod(String signMethod) {
        SignMethod = signMethod;
    }
}
