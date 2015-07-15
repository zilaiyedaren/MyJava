package org.myself.mobile.web.action.wechat;

import com.thoughtworks.xstream.XStream;
import org.myself.mobile.web.action.wechat.gson.bean.WxPayRequest;
import org.myself.mobile.web.action.wechat.gson.bean.WxPayResponse;
import org.myself.mobile.web.action.wechat.gson.util.Tools;
import org.myself.mobile.web.action.wechat.gson.util.XStreamFactory;
import org.myself.mobile.web.wxpay.CommonUtil;
import org.myself.mobile.web.wxpay.WxPayHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;


public class WxNativePayHandler extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(WxNativePayHandler.class);

    private String AppId = "wxf8b4f85f3a794e77";
    private String AppKey = "2Wozy2aksie1puXUBpWD8oZxiD1DfQuEaiC7KcRATv1Ino3mdopKaPGQQ7TtkNySuAmCaDCrw4xhPY5qKTBl7Fzm0RgR3c0WaVYIXZARsxzHV2x7iwPPzOz94dnwPWSn";
    private String SignType = "sha1";
    private String PartnerKey = "8934e7d15453e97507ef794cf7b0519d";

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.error("you are making the wrong request type");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        ServletInputStream in = request.getInputStream();
        // 转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);

        xs.alias("xml", WxPayRequest.class);
        String xmlMsg = Tools.inputStream2String(in);
        WxPayRequest wxRequest = (WxPayRequest) xs.fromXML(xmlMsg);
        WxPayResponse wxPayResponse = new WxPayResponse();
        try {
            // 取得消息类型
            String requestSignature = wxRequest.getAppSignature();
            WxPayHelper wxPayHelper = new WxPayHelper();
            HashMap<String, String> bizObj = new HashMap<String, String>();
            bizObj.put("appid", AppId);
            bizObj.put("appkey", AppKey);
            bizObj.put("productid", "10086");
            bizObj.put("timestamp", wxRequest.getTimeStamp());
            bizObj.put("noncestr", wxRequest.getNonceStr());
            bizObj.put("openid", wxRequest.getOpenId());
            bizObj.put("issubscribe", wxRequest.getIsSubscribe());
            String compareSignature = wxPayHelper.GetBizSign(bizObj);
            if (requestSignature != null && requestSignature.equalsIgnoreCase(compareSignature)) {
                wxPayHelper.SetParameter("bank_type", "WX");
                wxPayHelper.SetParameter("body", "test");
                wxPayHelper.SetParameter("partner", "1900000109");
                wxPayHelper.SetParameter("out_trade_no", CommonUtil.CreateNoncestr());
                wxPayHelper.SetParameter("total_fee", "1");
                wxPayHelper.SetParameter("fee_type", "1");
                wxPayHelper.SetParameter("notify_url", "http://218.242.214.85/mhlidu/wxpay/pay_result.dhtml");
                wxPayHelper.SetParameter("spbill_create_ip", "127.0.0.1");
                wxPayHelper.SetParameter("input_charset", "GBK");
                String packageStr = wxPayHelper.GetCftPackage();
                HashMap<String, String> newbizObj = new HashMap<String, String>();
                newbizObj.put("appid", AppId);
                newbizObj.put("appkey", AppKey);
                newbizObj.put("package", packageStr);
                newbizObj.put("timestamp", Long.toString(new Date().getTime() / 1000));
                newbizObj.put("noncestr", CommonUtil.CreateNoncestr());
                newbizObj.put("retcode", "0");
                newbizObj.put("retermsg", "success");
                String appSignature = wxPayHelper.GetBizSign(newbizObj);

                wxPayResponse.setAppId(AppId);
                wxPayResponse.setAppSignature(appSignature);
                wxPayResponse.setPackage(packageStr);
                wxPayResponse.setTimeStamp(Long.toString(new Date().getTime() / 1000));
                wxPayResponse.setSignMethod("sha1");
                wxPayResponse.setNonceStr(CommonUtil.CreateNoncestr());
                wxPayResponse.setRetCode("0");
                wxPayResponse.setRetErrMsg("success");
            }
        } catch (Exception e) {
            logger.error("response wxpay error", e);
        }
        // 把发送发送对象转换为xml输出
        xs = XStreamFactory.init(false);
        xs.alias("xml", WxPayResponse.class);
        System.out.println(wxPayResponse.toString());
        xs.toXML(wxPayResponse, response.getWriter());
    }
}
