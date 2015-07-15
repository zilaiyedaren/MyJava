package org.myself.mobile.web.action.demo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-25
 * Time: 下午8:43
 * To change this template use File | Settings | File Templates.
 */
public class DemoAction extends BaseAction {
    @Action("index")
    public String index(){
        return SUCCESS;
    }

    @Action("constants")
    public String constants(){
        //首先读取文件头部，然后写入来自网页输入内容，再次读取文件尾部，最后将所有内容一并写入到指定文件夹的文件中
        return SUCCESS;
    }
    @Action(value ="constants_info",results = {@Result(type="chain",location = "success")})
    public String   constants_info(){
          List<String> content=new ArrayList<String>();
        content.add("package com.derbysoft.mhotel.web.utils;");
        content.add("public class Constants {");
          content.add("public static final String hotelPassport =\"AA55718D0FDA669A4418B418985440B593FE\";");
          content.add("public static final String bookingPhone = \"0773-7956666\";");
          content.add("public static final String hotelName = \"阳朔芭堤雅酒店\";");
          content.add("public static final String hotelNameEn = \"Yangshuo Pattaya Hotel\";");
          content.add("public static final String hotelTitle = \"阳朔芭堤雅酒店\";");
          content.add("public static final String mserverUrl = \"http://218.242.214.85/XXX/\";");
          content.add("public static final String globleIP = \"211.144.87.40\";");
          content.add("public static final String dhServiceUrl = \"http://211.144.87.40/dhotelier/remoting/DHotelierHessianService\";");
          content.add("public static final String dhPicPath = \"http://211.144.87.40/dhfs/\";");
          content.add("public static final String serverPath = \"http://211.144.87.40/\";");
          content.add("public static final String DR6_APPAPI_URL = \"http://211.144.87.40/dhotelier/\";");
          content.add("public static final String BOOKINGLOG_URL = \"http://218.242.214.84:8012/appsl/\";");
          content.add("public static final String ERROR_LOGIN_NAME_DUPLICATE = \"-1025\";");
          content.add("public static final String ERROR_EMAIL_DUPLICATE = \"-1024\";");
          content.add("public static final String DEFAULT_MEMBER = \"mobile_FIT\";");
          content.add("}");
        if( Utils.writerFile(filePath,content)){
            System.out.println("success");
        }else{
            System.out.println("failure");
        }
        return SUCCESS;
    }



    @Action("maps")
    public String maps(){

        return SUCCESS;
    }
    @Action(value="maps_info" ,results = {@Result(type = "chain", location ="success")})
    public String maps_info(){
        if(latitude==""||longitude==""||text==""||hotelName==""||"".equalsIgnoreCase(latitude)){
            return null;
        }
        List<String> content= Utils.readFileByLines("D:\\test\\demo\\maps\\baidu_map.html");
        if( Utils.writerFile(filePath,content)){
            System.out.println("success");
        }else{
            System.out.println("failure");
        }
        return SUCCESS;
    }
    @Action("success")
    public String success(){
        return SUCCESS;
    }


    @Action("contact")
    public  String contact(){
        return SUCCESS;
    }
    @Action(value = "contact_info",results = {@Result(type="chain",location="success")})
    public String contact_info(){
        List<String> content= Utils.readFileByLines("D:\\test\\demo\\contact\\contact.jsp");

        content.add("<div  class=\"contact minw300\">Address：No.236,Sanya Bay Road, Sanya City, Hainan Province, China</div>");
        content.add("<div  class=\"contact minw320\">Zip Code：572000</div>");
        content.add("<div align=\"left\" style=\"padding-left:10px;\">Tel：");
        content.add(" <a data-role=\"button\" data-inline=\"true\" data-direction=\"reverse\"\n" +
                "href=\"tel:<s:property value=\"bookingPhone\"/>\"  id=\"search_results_call_for_help\"> +86(898)38898888\n" +
                "</a></div>");
        content.add("<div  class=\"contact minw320\">Fax：+86(898)38897777</div>");
        content.add("<div  class=\"contact minw320\">E-mail：<a href=\"mailto:sales.sanya@naradasanya.com\">sales.sanya@naradasanya.com</a></div>");
        content.add("<div  class=\"contact minw320\">Website：<a href=\"http://www.naradasanya.com\">www.naradasanya.com</a></div>");
        content.add("<div class=\"cut-line w\"></div><br/><div data-inline=\"true\"><br/>" +
                "<a href=\"../maps/baidu_map.html\" data-role=\"button\" data-theme=\"c\"> Geographical Location</a>");
         content.add("</div></li></s:if><s:else>");
        //中文
        content.add("<li data-theme=\"c\"  style=\"padding: 0.1em\">");
        content.add("<div  class=\"contact minw300\">地址：中国 海南省三亚市三亚湾路236号</div>");
        content.add("<div  class=\"contact minw320\">邮编：572000</div>");
        content.add("<div align=\"left\" style=\"padding-left:10px;\">电话：<a data-role=\"button\" data-inline=\"true\" data-direction=\"reverse\"" +
                "href=\"tel:<s:property value=\"bookingPhone\"/>\"  id=\"search_results_call_for_help\"> +86(898)38898888</a></div>");
        content.add("<div  class=\"contact minw320\" >传真：+86(898)38897777</div>");
        content.add("<div  class=\"contact minw320\">邮件：<a href=\"mailto:sales.sanya@naradasanya.com\">sales.sanya@naradasanya.com</a></div>");
        content.add("<div  class=\"contact minw320\">酒店官网：<a href=\"http://www.naradasanya.com\">www.naradasanya.com</a></div>");
        content.add("<div class=\"cut-line w\"></div><div data-inline=\"true\"  ><a href=\"../maps/baidu_map.html\" data-role=\"button\" data-theme=\"c\">地理位置</a></div>");
        content.add("</li></s:else></ul></form></div></body></html>");
                if( Utils.writerFile(filePath,content)){
            System.out.println("success");
        }else{
            System.out.println("failure");
        }

        return SUCCESS;
    }



    @Action("hotelinfo")
    public String hotelInfo(){
        return SUCCESS;
    }

    @Action("hotelmode")
    public String hotelMode(){
        return SUCCESS;
    }
    @Action("hotelaction")
    public String hotelAction(){
        List<String> content= Utils.readFileByLines("D:\\test\\demo\\hotelnfo\\HotelInfoAction.java");
        content.add("@Action(\"hotel_features\")");
        content.add("public String hotel_features() {");
        content.add("return SUCCESS;}");

        content.add("@Action(\"hotel_surroundings\")");
        content.add("public String hotel_surroundings() {");
        content.add("return SUCCESS;}");

        content.add("@Action(\"hotel_group\")");
        content.add(" public String hotel_group() {");
        content.add("return SUCCESS;}");

        content.add(" @Action(\"hotel_location\")");
        content.add("  public String  hotelLocation(){");
        content.add("return SUCCESS;}");

        content.add("private WSHotelResponse wsResponse;");
        content.add("public WSHotelResponse getWsResponse() {");
        content.add(" return wsResponse;}");

        content.add(" public void setWsResponse(WSHotelResponse wsResponse) {");
        content.add(" this.wsResponse = wsResponse;}");
        if( Utils.writerFile(filePath,content)){
            System.out.println("success");
        }else{
            System.out.println("failure");
        }
        return SUCCESS;
    }

    @Action("facilities")
    public String faciliteis(){
        return SUCCESS;
    }
    @Action("facilitiesmode")
    public String facilitiesMode(){
        return SUCCESS;
    }
    private String filePath;
    private String hotelName;
    private String text;
    private String latitude;
    private String longitude;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
