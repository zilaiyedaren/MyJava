package org.myself.mobile.web.action.wxpay;


import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.HttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.myself.mobile.web.wxpay.CommonUtil;
import org.myself.mobile.web.wxpay.MD5SignUtil;
import org.myself.mobile.web.wxpay.SDKRuntimeException;
import org.springframework.http.HttpStatus;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class WxPayTest2 {
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
            nativeObj.put("mch_id",MchId);
            nativeObj.put("body","test");
            nativeObj.put("nonce_str", CommonUtil.CreateNoncestr(10).toLowerCase());
            nativeObj.put("openid", "owlDvsraWLApAfq2FPjSYcU92B6g");
            nativeObj.put("out_trade_no", "15897556802");
            nativeObj.put("spbill_create_ip", "127.0.0.1");
            nativeObj.put("total_fee", "1");
            nativeObj.put("notify_url", "http://218.242.214.85/mhlidu/wxpay/pay_result.dhtml");
            nativeObj.put("trade_type", "JSAPI");
            String sign=GetBizSign(nativeObj);
            String xml=getXML(sign,nativeObj);
            System.out.println(xml);
            String apiurl="https://api.mch.weixin.qq.com/pay/unifiedorder";
            HttpClient httpclient = null;
            try {
                PostMethod method = new PostMethod(apiurl);
                method.setRequestEntity(new StringRequestEntity(xml, "application/json", "UTF-8"));
                org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
                int statusCode = client.executeMethod(method);
                if (statusCode == HttpStatus.OK.value()) {
                    String xmlStr=new String(method.getResponseBodyAsString());
                    System.out.println(xmlStr);  //返回xml

                    try {
                        Document document = DocumentHelper.parseText(xmlStr);
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
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (SDKRuntimeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static String getSign(HashMap<String, String> bizObj) throws SDKRuntimeException{
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
        String md5String=GetBizSign(bizParameters);
        return md5String;
    }
    public static String getXML(String sign,HashMap<String, String> nativeObj){

        String xml="<xml><appid>"+AppId+"</appid>"+
                "<mch_id>"+MchId+"</mch_id>"+
                "<nonce_str>"+nativeObj.get("nonce_str")+"</nonce_str>" +
                "<body>"+nativeObj.get("body")+"</body>" +
                "<out_trade_no>"+nativeObj.get("out_trade_no")+"</out_trade_no>" +
                "<total_fee>"+nativeObj.get("total_fee")+"</total_fee>" +
                "<spbill_create_ip>"+nativeObj.get("spbill_create_ip")+"</spbill_create_ip>" +
                "<notify_url>"+nativeObj.get("notify_url")+"</notify_url>" +
                "<openid>"+nativeObj.get("openid")+"</openid>" +
                "<trade_type>JSAPI</trade_type>" +
                "<sign>"+sign+"</sign>" +
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



        String bizString = CommonUtil.FormatBizQueryParaMap(bizParameters, false);
        String md5String= MD5SignUtil.Sign(bizString, PartnerKey);

        System.out.println(md5String);
        return  md5String;
    }


}
