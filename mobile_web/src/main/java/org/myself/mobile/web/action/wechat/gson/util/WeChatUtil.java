package org.myself.mobile.web.action.wechat.gson.util;

import com.google.gson.Gson;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.myself.mobile.web.action.wechat.gson.bean.cusmessage.Article;
import org.myself.mobile.web.action.wechat.gson.bean.cusmessage.ArticleDetail;
import org.myself.mobile.web.action.wechat.gson.bean.cusmessage.BroadcastMessage;
import org.myself.mobile.web.action.wechat.gson.bean.cusmessage.MessNews;
import org.myself.mobile.web.utils.Constants;
import org.myself.mobile.web.utils.JsonStrReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 13-11-25
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class WeChatUtil {
    protected static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);

    public static void main(String[] args) {
        sendBroadcastMessage("测试酒店通过微信全天无限制向所有关注者推送自定义消息");
    }

    public static String getAccessToken() {

        //第三方用户唯一凭证

        String appId = "xxx";

        // 第三方用户唯一凭证密钥

        String appSecret = "xxxx";

        String access_token_Uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        HttpEntity httpEntity = null;
        try {
            HttpGet httpGet = new HttpGet(access_token_Uri);
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                if (httpEntity != null) {
//                    System.out.println(JsonStrReader.read(httpEntity));
                    JSONObject demoJson = new JSONObject(JsonStrReader.read(httpEntity));
                    return demoJson.getString("access_token");
                }
            }
        } catch (Exception e) {
            logger.error("*** Error get data from dh due to [{}]", e.getMessage());
        }
        return null;
    }

    public static String sendBroadcastMessage(String content) {
        String accessToken = getAccessToken();
        String send_message_Uri = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        String get_followers_Uri = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
        HttpEntity httpEntity = null;
        try {
            HttpGet httpGet = new HttpGet(get_followers_Uri);
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode1 = response.getStatusLine().getStatusCode();
            if (statusCode1 == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                if (httpEntity != null) {
//                    System.out.println(JsonStrReader.read(httpEntity));
                    JSONObject demoJson = new JSONObject(JsonStrReader.read(httpEntity));
                    JSONObject data = new JSONObject(demoJson.getString("data"));
                    JSONArray array = data.getJSONArray("openid");
                    for (int i = 0; i < array.length(); i++) {
//            Broadcast cast = new Broadcast();
//            cast.setMsgtype("text");
//            cast.setTouser("o9OuWt7MPDUpJEQH4FxGvAwYouNA");
//            BroadcastContent text = new BroadcastContent();
//            text.setContent(content + "<a href='http://211.144.87.228/mhshibo/promotion/promotion_list.dhtml'>更多优惠促销</a>");
////          text.setContent(content + "");
//            cast.setText(text);
                        BroadcastMessage message = new BroadcastMessage();
                        message.setMsgtype("news");
//                        message.setTouser("o9OuWt7MPDUpJEQH4FxGvAwYouNA");
                        message.setTouser((String) array.get(i));
                        MessNews news = new MessNews();
                        ArticleDetail detail = new ArticleDetail();
                        detail.setTitle("酒店消息通知");
                        detail.setDescription(content + "  (测试酒店通过微信全天无限制向所有关注者推送自定义消息)");
                        detail.setPicurl(Constants.mserverUrl + "images/dinner_big04.jpg");
                        detail.setUrl(Constants.mserverUrl + "promotion/promotion_list.dhtml");
                        news.setArticles(new Article[]{detail});
                        message.setNews(news);
                        String body = (new Gson()).toJson(message);
                        PostMethod method = new PostMethod(send_message_Uri);
                        method.setRequestEntity(new StringRequestEntity(body, "application/json", "UTF-8"));
                        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
                        int statusCode = client.executeMethod(method);
//                        if (statusCode == HttpStatus.OK.value()) {
//                            return method.getResponseBodyAsString();
//                        }
                        logger.info(((String) array.get(i)) + statusCode);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("*** Error send broadcast due to [{}]", e.getMessage());
        }
        return null;
    }

//    protected static String generateJsonRequest(String content) {
//        JSONStringer js = new JSONStringer();
//        try {
//            js.object();
////            js.key("access_token").value(getAccessToken());
//            js.key("msgtype").value("text");
//            JSONStringer subjs = new JSONStringer();
//            subjs.object();
//            subjs.key("content").value(content + "<br><a href='http://211.144.87.228/mhshibo/promotion/promotion_list.dhtml'>更多优惠促销</a>");
//            subjs.endObject();
//            js.key("text").value(subjs.toString());
//            js.key("touser").value("o9OuWt7MPDUpJEQH4FxGvAwYouNA");
//            js.endObject();
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return js.toString();
//        }
//        return js.toString();
//    }
}
