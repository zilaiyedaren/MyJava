package org.myself.mobile.web.action.wechat;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.myself.mobile.web.action.wechat.gson.bean.Button;
import org.myself.mobile.web.action.wechat.gson.bean.ComplexButton;
import org.myself.mobile.web.action.wechat.gson.bean.Menu;
import org.myself.mobile.web.action.wechat.gson.bean.MenuButton;
import org.myself.mobile.web.utils.Constants;
import org.myself.mobile.web.utils.JsonStrReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 13-11-25
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class CreateMenu {

    protected static Logger logger = LoggerFactory.getLogger(CreateMenu.class);


    public static void main(String args[]) throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        System.out.println(args.length);
//        String accessToken = WeChatUtil.getAccessToken();
//        createMenu(accessToken);
    }

    public static void createMenu(String accessToken) {
        String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        HttpEntity httpEntity = null;
        try {
            MenuButton button1 = new MenuButton();
            button1.setName("酒店介绍");
            button1.setType("click");
            button1.setKey("brief");
            MenuButton button2 = new MenuButton();
            button2.setName("房型介绍");
            button2.setType("click");
            button2.setKey("room");
            MenuButton button3 = new MenuButton();
            button3.setName("酒店设施");
            button3.setType("click");
            button3.setKey("facility");
            MenuButton button4 = new MenuButton();
            button4.setName("优惠促销");
            button4.setType("view");
            button4.setUrl(Constants.mserverUrl + "promotion/promotion_list.dhtml");
            button4.setKey("promotion");
            MenuButton button5 = new MenuButton();
            button5.setName("酒店新闻");
            button5.setType("view");
            button5.setUrl(Constants.mserverUrl + "news/news_list.dhtml");
            button5.setKey("news");
            ComplexButton complex1 = new ComplexButton();
            complex1.setName("酒店信息");
            complex1.setSub_button(new MenuButton[]{button1, button2, button3,button4,button5});


            MenuButton button6 = new MenuButton();
//            button6.setName("微信支付demo");
//            button6.setType("view");
//            button6.setUrl("http://218.242.214.85/mhlidu/demo/home.html");
//            button6.setName("房间预订");
//            button6.setType("view");
//            button6.setUrl(Constants.mserverUrl + "booking/rooms_search.dhtml?from=wechat");
            button6.setName("房间预订");
            button6.setType("click");
            button6.setKey("book");

            MenuButton button7 = new MenuButton();
            button7.setName("会员绑定");
            button7.setType("click");
            button7.setKey("bind");

            MenuButton button8 = new MenuButton();
            button8.setName("会员注册");
            button8.setType("view");
            button8.setUrl(Constants.mserverUrl + "register.dhtml");

            MenuButton button9 = new MenuButton();
            button9.setName("订单查询");
            button9.setType("click");
            button9.setKey("reservation");


            MenuButton button10 = new MenuButton();
            button10.setName("忘记密码");
            button10.setType("view");
            button10.setUrl(Constants.mserverUrl + "forgetpassword.dhtml");
            ComplexButton complex2 = new ComplexButton();
            complex2.setName("微预订");
            complex2.setSub_button(new MenuButton[]{button6, button7, button8, button9, button10});


            MenuButton button11 = new MenuButton();
            button11.setName("今日天气");
            button11.setType("view");
            button11.setUrl(Constants.mserverUrl + "weather/weather.dhtml");
            MenuButton button12 = new MenuButton();
            button12.setName("酒店位置");
            button12.setType("view");
            button12.setUrl(Constants.mserverUrl + "maps/baidu_map.html");
            MenuButton button13=new MenuButton();
            button13.setName("联系我们");
            button13.setType("view");
            button13.setUrl(Constants.mserverUrl + "contact/contact.dhtml");
            ComplexButton complex3 = new ComplexButton();
            complex3.setName("更多");
//            complex3.setName("Activities");
            complex3.setSub_button(new MenuButton[]{button11, button12});

            Menu menu = new Menu();
            menu.setButton(new Button[]{complex1,complex2,complex3});

//            String body = (new Gson()).toJson(menu);
//            System.out.println(body);
            JSONObject jsonObject = JSONObject.fromObject(menu);
            String body = jsonObject.toString();
            System.out.println(body);
            PostMethod method = new PostMethod(createMenuUrl);
            method.setRequestEntity(new StringRequestEntity(body, "application/json", "UTF-8"));
//            method.setRequestBody(body);
            org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.OK.value()) {
//                httpEntity = response.getEntity();
//                return JsonStrReader.read(httpEntity);
                System.out.println(method.getResponseBodyAsString());
            }
        } catch (Exception e) {
            logger.error("*** Error get data from dh due to [{}]", e.getMessage());
        }
    }

    public static void deleteMenu(String accessToken) {
        String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
        HttpEntity httpEntity = null;
        try {
            HttpGet httpGet = new HttpGet(createMenuUrl);
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                if (httpEntity != null) {
//                    System.out.println(JsonStrReader.read(httpEntity));
                    JSONObject demoJson = JSONObject.fromObject(JsonStrReader.read(httpEntity));
                    System.out.println(demoJson.getString("errmsg"));
                }
            }
        } catch (Exception e) {
            logger.error("*** Error get data from dh due to [{}]", e.getMessage());
        }
    }

    public static void searchMenu(String accessToken) {
        String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
        HttpEntity httpEntity = null;
        try {
            HttpGet httpGet = new HttpGet(createMenuUrl);
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                if (httpEntity != null) {
//                    System.out.println(JsonStrReader.read(httpEntity));
//                    JSONObject demoJson = new JSONObject(JsonStrReader.read(httpEntity));
                    System.out.println(JsonStrReader.read(httpEntity));
                }
            }
        } catch (Exception e) {
            logger.error("*** Error get data from dh due to [{}]", e.getMessage());
        }
    }

}
