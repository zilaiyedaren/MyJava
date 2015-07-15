package org.myself.mobile.web.action.wxpay;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.HttpClient;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.myself.mobile.web.wxpay.CommonUtil;
import org.myself.mobile.web.wxpay.MD5SignUtil;
import org.myself.mobile.web.wxpay.SDKRuntimeException;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-3
 * Time: 下午7:51
 * To change this template use File | Settings | File Templates.
 */
public class WxPayTest {
    //北京建国
    private static String SignType = "MD5";
    public static String AppId = "wx421e68c844ef19c9";
    public static String PartnerKey = "8c4fe4b60a343a349188e4e11a5e0d5f";
    public static String PartnerID = "1223667001";
    public static String MchId="10029808";

    private static String pachage;


    public static void main(String[] args){

        try {
            HashMap<String, String> nativeObj = new HashMap<String, String>();
            nativeObj.put("appid",AppId);
            nativeObj.put("body","test");
            nativeObj.put("mch_id",MchId);
            nativeObj.put("nonce_str", CommonUtil.CreateNoncestr().toLowerCase());
            nativeObj.put("out_trade_no", "15897556820");
            nativeObj.put("spbill_create_ip", "127.0.0.1");
            nativeObj.put("total_fee", "1");
            nativeObj.put("notify_url", "http://211.144.87.228/mhjianguo/wxpay/pay_result.dhtml");
            nativeObj.put("openid", "owlDvsraWLApAfq2FPjSYcU92B6g");
            nativeObj.put("trade_type", "JSAPI");
            String md5String=GetBizSign(nativeObj);
            String xml=getXML(md5String,nativeObj);
            String apiurl="https://api.mch.weixin.qq.com/pay/unifiedorder";
            HttpClient httpclient = null;

            PostMethod method = new PostMethod(apiurl);
            method.setRequestEntity(new StringRequestEntity(xml, "application/xml", "UTF-8"));  //不需要参数名
            org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.OK.value()) {
                String xmlStr=new String(method.getResponseBodyAsString());
//                System.out.println(xmlStr);
                try {
                    org.dom4j.Document document = DocumentHelper.parseText(xmlStr);
                    Element root = document.getRootElement();
                    for(Iterator iterator = root.elementIterator("prepay_id");iterator.hasNext();){
                        Element prepay_id = (Element) iterator.next();
                        pachage=prepay_id.getName()+"="+prepay_id.getData();
                        System.out.println(pachage);
                    }
                } catch (DocumentException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (SDKRuntimeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static String getXML(String sign,HashMap<String, String> nativeObj){
          String xml="<xml>" +
                  "<appid>"+nativeObj.get("appid")+"</appid>"+
                  "<body>"+nativeObj.get("body")+"</body>" +
                  "<mch_id>"+nativeObj.get("mch_id")+"</mch_id>" +
                  "<nonce_str>"+nativeObj.get("nonce_str")+"</nonce_str>" +
                  "<notify_url>"+nativeObj.get("notify_url")+"</notify_url>" +
                  "<out_trade_no>"+nativeObj.get("out_trade_no")+"</out_trade_no>" +
                  "<spbill_create_ip>127.0.0.1</spbill_create_ip>" +
                  "<total_fee>1</total_fee>" +
                  "<trade_type>JSAPI</trade_type>" +
                  "<sign>"+sign+"</sign>" +
                  "<openid>owlDvsraWLApAfq2FPjSYcU92B6g</openid>" +
                  "</xml>";
        return xml;
    }

    public static String GetBizSign(HashMap<String, String> bizObj)
            throws SDKRuntimeException {
        HashMap<String, String> bizParameters = new HashMap<String, String>();

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                bizObj.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            if (item.getKey() != "") {
                bizParameters.put(item.getKey().toLowerCase(), item.getValue());
            }
        }

        if (PartnerKey == "") {
            throw new SDKRuntimeException("APPKEYΪ�գ�");
        }

        String bizString = CommonUtil.FormatBizQueryParaMap(bizObj, false);
        System.out.println(bizString);
        String md5String= MD5SignUtil.Sign(bizString, PartnerKey);

        return  md5String;
    }

}
