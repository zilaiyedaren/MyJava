package org.myself.mobile.web.action.wxpay;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-3
 * Time: 下午8:43
 * To change this template use File | Settings | File Templates.
 */
public class WxPayRequest {
    private String appd;
    private String attach;
    private String body;
    private String device_info;
    private String mch_id;
    private String nonce_str;
    private String notify_url;
    private String out_trade_no;
    private String spbill_create_ip;
    private String total_fee;
    private String trade_type;
    private String sign;

    @Override
    public String toString() {
        return "WxPayRequest{" +
                "appd='" + appd + '\'' +
                ", attach='" + attach + '\'' +
                ", body='" + body + '\'' +
                ", device_info='" + device_info + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
