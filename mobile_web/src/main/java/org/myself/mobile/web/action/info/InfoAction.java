package org.myself.mobile.web.action.info;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.*;
import org.myself.mobile.web.utils.JsonStrReader;
import org.springframework.http.HttpStatus;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.org.json.JSONStringer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-6
 * Time: 下午6:51
 * To change this template use File | Settings | File Templates.
 */
public class InfoAction extends BaseAction {
    @Action("info")
    public String info(){
        return SUCCESS;
    }
    @Action("weather")
    public String weather() throws Exception{
        String city="崇阳";
        String apiUri="http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=4fc8775c60deb54f74fe5b432ce36345";
        HttpEntity httpEntity = null;
        info = new WeatherInfo();
        try {
            HttpGet httpGet = new HttpGet(apiUri);
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                JSONObject jsonObject = new JSONObject(JsonStrReader.read(httpEntity));
                JSONArray jsonArray=jsonObject.optJSONArray("results");
                JSONObject jsonObject1=jsonArray.getJSONObject(0);
                JSONArray jsonArray1=jsonObject1.optJSONArray("weather_data");
                JSONArray jsonArray2=jsonObject1.optJSONArray("index");
                JSONObject jsonObject2=jsonArray1.getJSONObject(0);
                JSONObject jsonObject3=jsonArray2.getJSONObject(0);

//                System.out.println(jsonObject2);
                System.out.println(jsonArray2);
                info.setCity(city);
                info.setDate_y(jsonObject2.getString("date"));
//                info.setWeek(jsonObject2.getString("date"));
                info.setTemp1(jsonObject2.getString("temperature"));
                info.setWeather1(jsonObject2.getString("weather"));
                info.setWind1(jsonObject2.getString("wind"));
                info.setIndex_d(jsonObject3.getString("des"));
                info.setPicUrl(jsonObject2.getString("dayPictureUrl"));
            }
        } catch (Exception e) {
//            logger.error("*** Error get data from mobile.weather due to [{}]", e.getMessage());
        } finally {
            EntityUtils.consume(httpEntity);
        }
        return SUCCESS;
    }

    @Action("caipiao")
    public String caiPiao(){
        return SUCCESS;
    }
    @Action("caipiao_info")
    public String caipiao_info() throws Exception{
//        String code="utf8";
//        String type="json";
//        String rows="10";
//        http://f.opencai.net/utf8/ctfc22x5-10.json
//        String apiUri="http://f.opencai.net/utf8/"+caipiaoCode+"-10.json";
        String apiUri="http://f.opencai.net/utf8/"+caipiaoCode+"-10.json";
        HttpEntity httpEntity = null;
        HttpGet httpGet = new HttpGet(apiUri);
        try {
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                JSONObject jsonObject = new JSONObject(JsonStrReader.read(httpEntity));
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                caipiaoInfoList=new ArrayList<CaipiaoInfo>();
                JSONObject jo=null;
                for(int i=0;i<jsonArray.length();i++){
                    caipiaoInfo=new CaipiaoInfo();
                    jo=jsonArray.getJSONObject(i);
                    caipiaoInfo.setCaiExpect(jo.getString("expect"));
                    caipiaoInfo.setCaiOpentime(jo.getString("opentime"));
                    caipiaoInfo.setCaiOpenCode(jo.getString("opencode"));
                    caipiaoInfoList.add(caipiaoInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }

        return SUCCESS;
    }
    @Action("express")
    public String express(){
        return SUCCESS;
    }
    @Action("express_info")
    public String express_info() throws Exception{
//        http://www.kuaidi100.com/query?type=快递公司代号&postid=快递单号
//        ps:快递公司编码:申通="shentong" EMS="ems" 顺丰="shunfeng" 圆通="yuantong" 中通="zhongtong"
//        韵达="yunda" 天天="tiantian" 汇通="huitongkuaidi" 全峰="quanfengkuaidi" 德邦="debangwuliu" 宅急送="zhaijisong"
        String apiUrl="http://www.kuaidi100.com/query?type=shentong&postid=111111111111";
        HttpEntity httpEntity = null;
        HttpGet httpGet = new HttpGet(apiUrl);
        try {
            HttpResponse response = new DefaultHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                JSONObject jsonObject = new JSONObject(JsonStrReader.read(httpEntity));
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                expressInfoList=new ArrayList<ExpressInfo>();
                JSONObject jo=null;
                for(int i=0;i<jsonArray.length();i++){
                    expressInfo=new ExpressInfo();
                    jo=jsonArray.getJSONObject(i);
                    expressInfo.setExpressTime(jo.getString("time"));
                    expressInfo.setExpressContext(jo.getString("context"));
                    expressInfo.setExpressFtime(jo.getString("ftime"));
                    expressInfoList.add(expressInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }

        return SUCCESS;
    }
    @Action("newstype")
    public String newsType() throws Exception{
//        1、取得资讯信息列表
//        如：api.yi18.net/news/list
//        详情：api.yi18.net/news/list?id
//        2、取得资讯分类列表
//        如：api.yi18.net/news/newsclass
//        3、取得资讯信息详细
//        如：api.yi18.net/news/show?id=1
//        4、搜索资讯
//        如：api.yi18.net/news/search?keyword=健康
//        社会热点
//                食品新闻
//        药品新闻
//                生活贴士
//        医药新闻
//                企业要闻
        String apiUrl="http://api.yi18.net/news/newsclass";
        HttpEntity httpEntity = null;
        HttpPost httpPost = new HttpPost(apiUrl);

        try {
            HttpResponse response = new DefaultHttpClient().execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                httpEntity = response.getEntity();
                JSONObject jsonObject = new JSONObject(JsonStrReader.read(httpEntity));
                JSONArray jsonArray=jsonObject.getJSONArray("yi18");
                newsTypeList=new ArrayList<NewsType>();
                JSONObject jo=null;
                for(int i=0;i<jsonArray.length();i++){
                    newsType=new NewsType();
                    jo=jsonArray.getJSONObject(i);
                    newsType.setNewsTypeId(jo.getString("id"));
                    newsType.setNewsTypeName(jo.getString("name"));
                    newsTypeList.add(newsType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }
        return SUCCESS;
    }

    @Action("newslist")
    public String newslist() throws Exception{
        String apiUrl="http://api.yi18.net/news/list?id="+newsType_Id;
        String base="http://www.yi18.net/";
        HttpEntity httpEntity=null;
        HttpGet httpGet=new HttpGet(apiUrl);
        try {
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                JSONArray jsonArray=jsonObject.getJSONArray("yi18");
                newsInfoList=new ArrayList<NewsInfo>();
                JSONObject jo=null;
                for(int i=0;i<jsonArray.length();i++){
                    jo=jsonArray.getJSONObject(i);
                    newsInfo=new NewsInfo();
                    newsInfo.setNewsId(jo.getString("id"));
                    newsInfo.setNewsImg(base + jo.getString("img"));
                    newsInfo.setNewsTag(jo.getString("tag"));
                    newsInfo.setNewsTitle(jo.getString("title"));
                    newsInfo.setNewsTime(jo.getString("time"));
                    newsInfo.setNewsTime(jo.getString("count"));
                    newsInfoList.add(newsInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }
        return SUCCESS;
    }
    @Action("newslist_detail")
    public String newslist_detail() throws Exception{
        String apiUrl="http://api.yi18.net/news/show?id="+news_id;
        HttpEntity httpEntity=null;
        HttpGet httpGet=new HttpGet(apiUrl);

        try {
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                JSONObject jo=jsonObject.getJSONObject("yi18");
                 news=new NewsInfo();
                news.setNewsTag(jo.getString("tag"));
                news.setNewsTitle(jo.getString("title"));
                news.setNewsMessage(jo.getString("message"));
                news.setNewsCount(jo.getString("count"));
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }

        return SUCCESS;
    }

    @Action("stock")
    public String stock() throws Exception{
        String apiUrl="http://api.yi18.net/news/list";
        HttpEntity httpEntity = null;
        HttpPost httpPost = new HttpPost(apiUrl);

        return SUCCESS;
    }
    @Action("translate")
    public String translate(){
        return SUCCESS;
    }
    //一个action跳转到另一个action 跳转方式为chain 会保留request对象, redirectAction 则不能保留
    @Action(value="translate_info" ,results = {@Result(type = "chain", location = "translate")})
    public String translateInfo() throws Exception{
//        http://api.36wu.com/Translate/GetTranslate?q=%E6%88%91%E6%98%AF%E4%B8%AD%E5%9B%BD%E4%BA%BA%EF%BC%8C%E6%88%91%E7%88%B1%E6%88%91%E7%9A%84%E5%9B%BD%E5%AE%B6&format=auto
////        自动识别，format字段目前仅限五几种组合 zh_en 中 -> 英；zh_jp 中 -> 日；en_zh 英 -> 中；jp_zh 日 -> 中
        String text;
        String apiUrl="http://api.36wu.com/Translate/GetTranslate?q="+translateText.trim()+"&format="+translateType;
        HttpEntity httpEntity=null;
        HttpGet httpGet=new HttpGet(apiUrl);
        try {
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    translateInfo=new TranslateInfo();
                    translateInfo.setTranslateDst(jsonArray.getJSONObject(0).getString("dst"));
                    translateInfo.setTranslateSrc(jsonArray.getJSONObject(0).getString("src"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            EntityUtils.consume(httpEntity);
        }
        return SUCCESS;
    }

    @Action("weixin")
    public  String weiXin() throws Exception{
         String apiUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        HttpEntity httpEntity=null;
        JSONStringer js = new JSONStringer();

        try {
            js.object();
            js.key("touser").value("OPENID");
            js.key("template_id").value("ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY");
            js.key("url").value("http://weixin.qq.com/download");
            js.key("topcolor").value("#topcolor");
            js.key("data").value(data());
            js.endObject();
            String jsonString = js.toString();//将json转为String
            System.out.println(jsonString);
            HttpPost httpPost=new HttpPost(apiUrl);
            StringEntity stringEntity=new StringEntity(jsonString);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.OK.value()){
                 httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                System.out.println(jsonObject.toString()); //返回的结果
            }

        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }

        return SUCCESS;
    }

    private void getMap(LinkedHashMap m){  //按顺序输出Map中的Key
        Set set = m.keySet();
        Iterator iterator = set.iterator();
        String key=null;
        while(iterator.hasNext())
        {
            key = (String)iterator.next();
            System.out.println(key);
        }
    }
        private Map<String,Map> data(){ //获取
        Map<String,Map> dataMap=new LinkedHashMap<String, Map>();//使map那顺序存储
        Map map=new LinkedHashMap();
        map.put("value","黄先生");
        map.put("color","#173177");
        dataMap.put("User", map);
        Map map1=new LinkedHashMap();
        map1.put("value","06月07日 19时24分");
        map1.put("color","#173177");
        dataMap.put("Date",map1);
        Map map2=new LinkedHashMap();
        map2.put("value","0426");
        map2.put("color","#173177");
        dataMap.put("CardNumber",map2);
        Map map3=new LinkedHashMap();
        map3.put("value","消费");
        map3.put("color","#173177");
        dataMap.put("Type",map3);
        Map map4=new LinkedHashMap();
        map4.put("value","人民币260.00元");
        map4.put("color","#173177");
        dataMap.put("Money",map4);
        Map map5=new LinkedHashMap();
        map5.put("value","06月07日19时24分");
        map5.put("color","#173177");
        dataMap.put("DeadTime",map5);
        Map map6=new LinkedHashMap();
        map6.put("value","6504.09");
        map6.put("color","#173177");
        dataMap.put("Left",map6);

        return dataMap;
    }




    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String style;

    private String name;

    private NewsType newsType;

    private List<NewsType>  newsTypeList;

    private String newsType_Id;

    private NewsInfo newsInfo;

    private List<NewsInfo> newsInfoList;

    private String news_id;

    private NewsInfo news;

    private String expressName;

    private String expressOrder;

    private List<ExpressInfo> expressInfoList;

    private ExpressInfo expressInfo;

    private String caipiaoCode;

    private String caipiaoName;

    private CaipiaoInfo caipiaoInfo;

    private  List<CaipiaoInfo> caipiaoInfoList;

    private TranslateInfo translateInfo;

    private String  translateType;

    private String translateText;

    public String getTranslateText() {
        return translateText;
    }

    public void setTranslateText(String translateText) {
        this.translateText = translateText;
    }

    public String getTranslateType() {
        return translateType;
    }

    public void setTranslateType(String translateType) {
        this.translateType = translateType;
    }

    public TranslateInfo getTranslateInfo() {
        return translateInfo;
    }

    public void setTranslateInfo(TranslateInfo translateInfo) {
        this.translateInfo = translateInfo;
    }

    public List<CaipiaoInfo> getCaipiaoInfoList() {
        return caipiaoInfoList;
    }

    public void setCaipiaoInfoList(List<CaipiaoInfo> caipiaoInfoList) {
        this.caipiaoInfoList = caipiaoInfoList;
    }

    public String getCaipiaoCode() {
        return caipiaoCode;
    }

    public void setCaipiaoCode(String caipiaoCode) {
        this.caipiaoCode = caipiaoCode;
    }

    public String getCaipiaoName() {
        return caipiaoName;
    }

    public void setCaipiaoName(String caipiaoName) {
        this.caipiaoName = caipiaoName;
    }

    public List<ExpressInfo> getExpressInfoList() {
        return expressInfoList;
    }

    public void setExpressInfoList(List<ExpressInfo> expressInfoList) {
        this.expressInfoList = expressInfoList;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressOrder() {
        return expressOrder;
    }

    public void setExpressOrder(String expressOrder) {
        this.expressOrder = expressOrder;
    }

    public NewsInfo getNews() {
        return news;
    }

    public void setNews(NewsInfo news) {
        this.news = news;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public List<NewsInfo> getNewsInfoList() {
        return newsInfoList;
    }

    public void setNewsInfoList(List<NewsInfo> newsInfoList) {
        this.newsInfoList = newsInfoList;
    }


    public String getNewsType_Id() {
        return newsType_Id;
    }

    public void setNewsType_Id(String newsType_Id) {
        this.newsType_Id = newsType_Id;
    }

    public List<NewsType> getNewsTypeList() {
        return newsTypeList;
    }

    public void setNewsTypeList(List<NewsType> newsTypeList) {
        this.newsTypeList = newsTypeList;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public WeatherInfo getInfo() {
        return info;
    }

    public void setInfo(WeatherInfo info) {
        this.info = info;
    }

    private WeatherInfo info;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
