package org.myself.mobile.web.http.webservice;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.PlaneAirLine;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-21
 * Time: 下午2:03
 * To change this template use File | Settings | File Templates.
 */

/*HTTP POST and GET 请求和响应WebService*/
public class WebServicePostAction extends BaseAction{
//    get方法请求Webservice服务
//    @Action("get")
    public String get(){
    URL url;
    BufferedReader bin = null;
    StringBuilder result = new StringBuilder();
        try {
            String urlTemp = "http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx/getDomesticAirlinesTime?startCity="
                    + URLEncoder.encode("上海", "UTF-8")+"&lastCity="+URLEncoder.encode("武汉", "UTF-8")+"&theDate=2014-11-11&userID=";// URLEncoder用来参数编码
            url = new URL(urlTemp);
            InputStream in = url.openStream(); // 请求
            bin = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String s = null;
            while ((s = bin.readLine()) != null) {
                result.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bin) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            String xml=result.toString();
            Document dom= DocumentHelper.parseText(xml);
            Element root=dom.getRootElement();
            Element airlines = ((Element)root.elements().get(1)).element("Airlines");
            planeAirLine=new PlaneAirLine();
            planeAirLineList=new ArrayList<PlaneAirLine>();
            for(Iterator iter = airlines.elementIterator(); iter.hasNext();)
            {
                Element e = (Element)iter.next();   //获取AirlinesTime标签
                planeAirLine.setCompanyName(e.element("Company").getText());
                planeAirLine.setAirlineCode(e.element("AirlineCode").getText());
                planeAirLine.setStartDrome(e.element("StartDrome").getText());
                planeAirLine.setArriveDrome(e.element("ArriveDrome").getText());
                planeAirLine.setMode(e.element("Mode").getText());
                planeAirLine.setAirlineStop(e.element("AirlineStop").getText());
                planeAirLine.setWeek(e.element("Week").getText());
                planeAirLineList.add(planeAirLine);
            }

        } catch (DocumentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return SUCCESS;
    }

    /*POST方式*/
//    @Action("post")
    public String post(){
        OutputStreamWriter out = null;
        StringBuilder sTotalString = new StringBuilder();
        try {
            URL urlTemp = new URL( "http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx/getDomesticAirlinesTime");
            URLConnection connection = urlTemp.openConnection();
            connection.setDoOutput(true);
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            StringBuffer sb = new StringBuffer();
            sb.append("startCity=上海");
            sb.append("lastCity=武汉");
            sb.append("theDate=2014-11-11");
            sb.append("userID=");
            out.write(sb.toString());
            out.flush();
            String sCurrentLine="";
            InputStream l_urlStream;
            l_urlStream = connection.getInputStream();// 请求
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                    l_urlStream));
            while ((sCurrentLine = l_reader.readLine()) != null) {
                sTotalString.append(sCurrentLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(sTotalString.toString());
        return SUCCESS;
    }

    private PlaneAirLine planeAirLine;

    private List<PlaneAirLine> planeAirLineList;

    public List<PlaneAirLine> getPlaneAirLineList() {
        return planeAirLineList;
    }

    public void setPlaneAirLineList(List<PlaneAirLine> planeAirLineList) {
        this.planeAirLineList = planeAirLineList;
    }
}
