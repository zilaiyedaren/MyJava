package org.myself.mobile.web.action.convenience.plane;

import org.apache.struts2.convention.annotation.Action;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.PlaneAirLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-25
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class PlaneAction extends BaseAction {

    @Action("planeInfo")
    public String planeInfo(){
        URL url;
        BufferedReader bin = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlTemp = "http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx/getDomesticAirlinesTime?startCity="
                    + URLEncoder.encode(startCity, "UTF-8")+"&lastCity="+URLEncoder.encode(arriveCity, "UTF-8")+"&theDate="+theDate+"&userID=";// URLEncoder用来参数编码
            url = new URL(urlTemp);
            InputStream in = url.openStream(); // 请求
            bin = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String s = null;
            while ((s = bin.readLine())!= null) {
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
            planeAirLineList=new ArrayList<PlaneAirLine>();
            for(Iterator iter = airlines.elementIterator(); iter.hasNext();)
            {
                Element e = (Element)iter.next();   //获取AirlinesTime标签
                planeAirLine=new PlaneAirLine();
                planeAirLine.setCompanyName(e.element("Company").getText());
                planeAirLine.setAirlineCode(e.element("AirlineCode").getText());
                planeAirLine.setStartDrome(e.element("StartDrome").getText());
                planeAirLine.setArriveDrome(e.element("ArriveDrome").getText());
                planeAirLine.setStartTime(e.element("StartTime").getText());
                planeAirLine.setArriveTime(e.element("ArriveTime").getText());
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

    private String startCity;

    private String arriveCity;

    private String theDate;

    private PlaneAirLine planeAirLine;

    private List<PlaneAirLine> planeAirLineList;

    public List<PlaneAirLine> getPlaneAirLineList() {
        return planeAirLineList;
    }

    public void setPlaneAirLineList(List<PlaneAirLine> planeAirLineList) {
        this.planeAirLineList = planeAirLineList;
    }


    public String getTheDate() {
        return theDate;
    }

    public void setTheDate(String theDate) {
        this.theDate = theDate;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
    }
}
