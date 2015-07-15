package org.myself.mobile.web.action.convenience.train;

import org.apache.struts2.convention.annotation.Action;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.TrainInfo;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-25
 * Time: 下午5:35
 * To change this template use File | Settings | File Templates.
 */
public class TrainAction extends BaseAction {
    @Action("trainCode")
    public String trainCode(){
        return SUCCESS;
    }

    @Action("stationName")
    public String stationName(){
        return SUCCESS;
    }

    @Action("trainDetail")
    public String trainDeatil(){
        String apiUrl="http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx/getDetailInfoByTrainCode?TrainCode="+trainCode+"&UserID=";
        BufferedReader bufferedReader=null;
        StringBuilder stringBuilder=new StringBuilder();

        try {
            URL url=new URL(apiUrl);
            InputStream inputStream=url.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String s=null;
            while((s=bufferedReader.readLine())!=null){
                stringBuilder.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if(bufferedReader!=null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }
        try {
            Document document= DocumentHelper.parseText(stringBuilder.toString());
            Element root=document.getRootElement();
            Element trainDetailInfos=((Element)root.elements().get(1)).element("getDetailInfo");
            trainInfos=new ArrayList<TrainInfo>();
            for(Iterator iterator=trainDetailInfos.elementIterator();iterator.hasNext();){
                Element e=(Element)iterator.next();
                trainInfo=new TrainInfo();
                trainInfo.setLastStation(e.elementText("TrainStation"));
                trainInfo.setArriveTime(e.elementText("ArriveTime"));
                trainInfo.setStartTime(e.elementText("StartTime"));
                trainInfo.setKm(e.elementText("KM"));
                trainInfos.add(trainInfo);
            }
        } catch (DocumentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return SUCCESS;
    }

    @Action("trainInfo")
    public String trainInfo() throws Exception{
         String apiUrl="http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx/getStationAndTimeByStationName?StartStation="
                 +firstStation+"&ArriveStation="+arriveStation+"&UserID=";
        URL url;
        BufferedReader bufferedReader=null;
        StringBuilder stringBuilder=new StringBuilder();

        try {
            url=new URL(apiUrl);
            InputStream inputStream=url.openStream();
            bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String s=null;
            while((s=bufferedReader.readLine())!=null){
                stringBuilder.append(s);
            }
        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        Document document= DocumentHelper.parseText(stringBuilder.toString());
        Element root=document.getRootElement();
        Element timeTables=((Element)root.elements().get(1)).element("getStationAndTime");
        trainInfoList=new ArrayList<TrainInfo>();
        for(Iterator iterator=timeTables.elementIterator();iterator.hasNext();){
            Element e=(Element)iterator.next();
            trainInfo=new TrainInfo();
            trainInfo.setTrainCode(e.elementText("TrainCode"));
            trainInfo.setFirstStation(e.elementText("FirstStation"));
            trainInfo.setLastStation(e.elementText("LastStation"));
            trainInfo.setStartStation(e.elementText("StartStation"));
            trainInfo.setStartTime(e.elementText("StartTime"));
            trainInfo.setArriveStation(e.elementText("ArriveStation"));
            trainInfo.setArriveTime(e.elementText("ArriveTime"));
            trainInfo.setKm(e.elementText("KM"));
            trainInfo.setUseDate(e.elementText("UseDate"));
            trainInfoList.add(trainInfo);
        }
        return SUCCESS;
    }

    private String arriveStation;

    private String firstStation;

    private TrainInfo trainInfo;

    private List<TrainInfo> trainInfoList;

    private List<TrainInfo> trainInfos;

    private String trainCode;

    public List<TrainInfo> getTrainInfos() {
        return trainInfos;
    }

    public void setTrainInfos(List<TrainInfo> trainInfos) {
        this.trainInfos = trainInfos;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public List<TrainInfo> getTrainInfoList() {
        return trainInfoList;
    }

    public void setTrainInfoList(List<TrainInfo> trainInfoList) {
        this.trainInfoList = trainInfoList;
    }

    public String getArriveStation() {
        return arriveStation;
    }

    public void setArriveStation(String arriveStation) {
        this.arriveStation = arriveStation;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(String firstStation) {
        this.firstStation = firstStation;
    }
}
