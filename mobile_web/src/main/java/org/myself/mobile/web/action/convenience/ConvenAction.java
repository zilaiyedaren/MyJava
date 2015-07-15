package org.myself.mobile.web.action.convenience;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.*;
import org.myself.mobile.web.utils.JsonStrReader;
import org.springframework.http.HttpStatus;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-5
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class ConvenAction extends BaseAction {
    @Action("convenience")
    public String convenience(){
        return SUCCESS;
    }

    @Action("weather")
    public String weather() throws Exception{
        areaId="崇阳";
        String apiUri="http://api.map.baidu.com/telematics/v3/weather?location="+areaId+"&output=json&ak=4fc8775c60deb54f74fe5b432ce36345";
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
                info.setCity(areaId);
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

    @Action("illegal")
    public String illegal(){
        return SUCCESS;
    }
//    http://api.36wu.com/Illegal/GetIllegalInfo?city=xian&hphm=%E9%99%95A20L09&engineno=80075002
    @Action("illegalinfo")
    public String illegal_carinfo() throws Exception{
        String apiUrl="http://api.36wu.com/Illegal/GetIllegalInfo?city=xian&hphm=陕A20L09&engineno=80075002";
        HttpEntity httpEntity=null;

        try {
            HttpGet httpGet=new HttpGet(apiUrl);
            HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("lists");
                    JSONObject jb=null;
                    illegalInfoList =new ArrayList<IllegalInfo>();
                    int count=jsonArray.length();
                    for(int i=0;i<count;i++){
                        jb=jsonArray.getJSONObject(i);
                        illegalInfo=new IllegalInfo();
                        illegalInfo.setIllegalCount(count);
                        illegalInfo.setIllegalAct(jb.getString("act"));
                        illegalInfo.setIllegalAgency(jb.getString("agency"));
                        illegalInfo.setIllegalArea(jb.getString("area"));
                        illegalInfo.setIllegalChuli(jb.getString("chuli"));
                        illegalInfo.setIllegalDate(jb.getString("date"));
                        illegalInfo.setIllegalMoney(jb.getString("money"));
                        illegalInfo.setIllegalOrg_fen(jb.getString("org_fen"));
                        illegalInfoList.add(illegalInfo);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }
        return SUCCESS;
    }
    @Action("idcard")
    public String idCard(){
        return  SUCCESS;
    }
   @Action("idcardinfo")
   public String idcard_info() throws Exception{
       String apiUrl="http://api.36wu.com/IdCard/GetIdCardInfo?idcard=21030319630118474X";
       HttpEntity httpEntity=null;

       try {
           HttpGet httpGet=new HttpGet(apiUrl);
           HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
           int statusCode=httpResponse.getStatusLine().getStatusCode();
           if(statusCode==HttpStatus.OK.value()){
               httpEntity=httpResponse.getEntity();
               JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
               idCard=new IdCard();
               if(jsonObject.getInt("status")==200){
                    jsonObject=jsonObject.getJSONObject("data");
                   idCard.setIdCard(jsonObject.getString("idcard"));
                   idCard.setSex(jsonObject.getString("sex"));
                   idCard.setBirthday(jsonObject.getString("birthday"));
                   idCard.setAddress(jsonObject.getString("address"));
               }
           }
       }catch (JSONException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       } finally {
           EntityUtils.consume(httpEntity);
       }
       return SUCCESS;
   }
    @Action("mobiletel")
    public String mobileTel(){
//        {"status":200,"message":"OK","data":{"province":"浙江","city":"杭州","areaCode":"0571","postCode":"310000","corp":"中国移动","card":null}}
        return SUCCESS;
    }
    @Action("mobiletel_info")
    public String mobiletel_info() throws Exception{
        String apiUrl="http://api.36wu.com/Mobile/GetMobileOwnership?mobile=18758003012";
        HttpEntity httpEntity=null;
        try {
            HttpGet httpGet=new HttpGet(apiUrl);

            HttpResponse httpResponse =new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    jsonObject=jsonObject.getJSONObject("data");
                    mobilePhone=new MobilePhone();
    //                mobilePhone.setPhoneNum(mobileNum);
                    mobilePhone.setProvince(jsonObject.getString("province"));
                    mobilePhone.setCity(jsonObject.getString("city"));
                    mobilePhone.setAreaCode(jsonObject.getString("areaCode"));
                    mobilePhone.setPostCode(jsonObject.getString("postCode"));
                    mobilePhone.setCorp(jsonObject.getString("corp"));
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
     @Action("bus")
     public String bus(){
         return SUCCESS;
     }
     @Action("movie")
     public String movie(){
         return SUCCESS;
     }
     @Action("isbn")
     public String isbn(){
         return SUCCESS;
     }
    @Action("isbnInfo")
    public String isbnInfo() throws Exception{
        String apiUrl="http://api.36wu.com/ISBN/GetIsbnInfo?isbn=9787533935498";
        HttpEntity httpEntity=null;
        HttpGet httpGet=new HttpGet(apiUrl);
        HttpResponse httpResponse=new DefaultHttpClient().execute(httpGet);
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.OK.value()){
            httpEntity=httpResponse.getEntity();
            JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
            if(jsonObject.getInt("status")==200){
                jsonObject=jsonObject.getJSONObject("data");
                isbnInfo=new IsbnInfo();
                isbnInfo.setTitle(jsonObject.getString("title"));
                isbnInfo.setAuthor(jsonObject.getString("author"));
                isbnInfo.setAuthorInfo(jsonObject.getString("author_info"));
                isbnInfo.setPages(jsonObject.getString("pages"));
                isbnInfo.setPrice(jsonObject.getString("price"));
                isbnInfo.setPubdate(jsonObject.getString("pubdate"));
                isbnInfo.setPublisher(jsonObject.getString("publisher"));
                isbnInfo.setBinding(jsonObject.getString("binding"));
                isbnInfo.setSubTitle(jsonObject.getString("subtitle"));
                isbnInfo.setTranslator(jsonObject.getString("translator"));
                isbnInfo.setIsbn13(jsonObject.getString("isbn13"));
                isbnInfo.setIsbn10(jsonObject.getString("isbn10"));
            }
        }
        return SUCCESS;
    }

    @Action("plane")
    public String plane(){
        return SUCCESS;
    }

   @Action("train")
    public String train(){
       return SUCCESS;
   }

    private String areaId;

    private WeatherInfo info;

    private IllegalInfo illegalInfo;

    private List<IllegalInfo> illegalInfoList;

    private String carId;

    private String carEngineno;

    private IdCard idCard;

    private  String idcard;

    private String mobileNum;

    private MobilePhone mobilePhone;

    private String isbn;

    public String getIsbn() {
        return isbn;
    }
    private IsbnInfo isbnInfo;

    public IsbnInfo getIsbnInfo() {
        return isbnInfo;
    }

    public void setIsbnInfo(IsbnInfo isbnInfo) {
        this.isbnInfo = isbnInfo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public List<IllegalInfo> getIllegalInfoList() {
        return illegalInfoList;
    }

    public void setIllegalInfoList(List<IllegalInfo> illegalInfoList) {
        this.illegalInfoList = illegalInfoList;
    }

    public String getCarEngineno() {
        return carEngineno;
    }

    public void setCarEngineno(String carEngineno) {
        this.carEngineno = carEngineno;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public WeatherInfo getInfo() {
        return info;
    }

    public void setInfo(WeatherInfo info) {
        this.info = info;
    }
}
