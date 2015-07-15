package org.myself.mobile.web.action.wxpay;

import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.WeChatPay;
import org.myself.mobile.web.wxpay.CommonUtil;
import org.myself.mobile.web.wxpay.SDKRuntimeException;
import org.myself.mobile.web.wxpay.WxPayHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class WxpayAction extends BaseAction {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static String AppId = "wxf8b4f85f3a794e77";
    private static String AppKey = "2Wozy2aksie1puXUBpWD8oZxiD1DfQuEaiC7KcRATv1Ino3mdopKaPGQQ7TtkNySuAmCaDCrw4xhPY5qKTBl7Fzm0RgR3c0WaVYIXZARsxzHV2x7iwPPzOz94dnwPWSn";
    private static String SignType = "sha1";
    private static String PartnerKey = "8934e7d15453e97507ef794cf7b0519d";


    @Action("wx_pay")
    public String wxPay() throws SDKRuntimeException {
        WxPayHelper wxPayHelper = new WxPayHelper();
        //设置微信服务端验证信息
        wxPayHelper.SetAppId(AppId);
        wxPayHelper.SetAppKey(AppKey);
        wxPayHelper.SetPartnerKey(PartnerKey);
        wxPayHelper.SetSignType(SignType);
        // 设置请求参数
        wxPayHelper.SetParameter("bank_type", "WX");
        wxPayHelper.SetParameter("body", "test");
        wxPayHelper.SetParameter("partner", "1900000109");
        wxPayHelper.SetParameter("out_trade_no", CommonUtil.CreateNoncestr());
        wxPayHelper.SetParameter("total_fee", "1");
        wxPayHelper.SetParameter("fee_type", "1");
        wxPayHelper.SetParameter("notify_url", "http://218.242.214.85/mhlidu/wxpay/pay_result.dhtml");
        wxPayHelper.SetParameter("spbill_create_ip", "127.0.0.1");
        wxPayHelper.SetParameter("input_charset", "GBK");
        HashMap<String, String> nativeObj = wxPayHelper.createPayMap();

        weChatPay = new WeChatPay();
        weChatPay.setAppId(AppId);
        weChatPay.setSignType(SignType);
        weChatPay.setTimeStamp(nativeObj.get("timestamp"));
        weChatPay.setNonceStr(nativeObj.get("noncestr"));
        weChatPay.setPackageStr(nativeObj.get("package"));
        weChatPay.setPaySign(nativeObj.get("paySign"));

//        WeChatPay pay = new WeChatPay();
        return SUCCESS;
    }

    @Action("pay_result")
    public String payResult() throws SDKRuntimeException {

        return SUCCESS;
    }

    private WeChatPay weChatPay;

    public WeChatPay getWeChatPay() {
        return weChatPay;
    }

    public void setWeChatPay(WeChatPay weChatPay) {
        this.weChatPay = weChatPay;
    }
}
