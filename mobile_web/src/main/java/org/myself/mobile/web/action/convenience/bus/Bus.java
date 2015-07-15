package org.myself.mobile.web.action.convenience.bus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.bean.LineInfo;
import org.myself.mobile.web.bean.StationInfo;
import org.myself.mobile.web.bean.TransferInfo;
import org.myself.mobile.web.utils.JsonStrReader;
import org.springframework.http.HttpStatus;
import org.xml.sax.XMLReader;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.org.json.XML;

import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-18
 * Time: 下午8:23
 * To change this template use File | Settings | File Templates.
 */
public class Bus extends BaseAction {
    @Action("line")
    public String line(){
        return SUCCESS;
    }

    @Action("transfer")
    public String transfer(){
        return SUCCESS;
    }
    @Action("station")
    public String station(){
        return SUCCESS;
    }

    @Action("lineInfo")
    public String lineInfo() throws Exception{
       String apiUrl="http://api.36wu.com/Bus/GetLineInfo?city=%E5%8C%97%E4%BA%AC&line=21";
        HttpEntity httpEntity=null;

        try {
            HttpGet httpGet=new HttpGet(apiUrl);
            HttpResponse httpResponse =new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode== HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    lineInfoList=new ArrayList<LineInfo>();
                    JSONObject jb=null;
                    lineInfo=new LineInfo();
                    for(int i=0;i<jsonArray.length();i++){
                        jb=jsonArray.getJSONObject(i);
                        lineInfo.setInfo(jb.getString("info"));
                        lineInfo.setName(jb.getString("name"));
                        lineInfo.setStats(jb.getString("stats"));

                        lineInfoList.add(lineInfo);
                    }
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

    @Action("transferInfo")
    public String transferInfo() throws Exception{
        String apiUrl="http://api.36wu.com/Bus/GetTransferInfo?city=%E5%8C%97%E4%BA%AC&start=%E8%A5%BF%E7%9B%B4%E9%97%A8&end=%E5%A4%A9%E5%AE%89%E9%97%A8";
        HttpEntity httpEntity=null;

        try {
            HttpGet httpGet=new HttpGet(apiUrl);
            HttpResponse httpResponse =new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode== HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONObject("buses").getJSONArray("bus");
                    transferInfoList=new ArrayList<TransferInfo>();
                    JSONObject jb=null;
                    transferInfo =new TransferInfo();
                    JSONArray ja=null;
                    for(int i=0;i<jsonArray.length();i++){
                        jb=jsonArray.getJSONObject(i);
                        transferInfo.setDist(jb.getInt("dist"));
                        transferInfo.setFoot_dist(jb.getInt("foot_dist"));
                        transferInfo.setLast_foot_dist(jb.getInt("last_foot_dist"));
                        transferInfo.setTime(jb.getInt("time"));
                        ja=jb.getJSONObject("segments").getJSONArray("segment");
                        System.out.println(ja.length());
                        for(int j=0;j<ja.length();j++){
                            transferInfo.setStart_end(ja.getJSONObject(j).getString("end_stat"));
                            transferInfo.setDist_end(ja.getJSONObject(j).getInt("foot_dist"));
                            transferInfo.setLine_dist(ja.getJSONObject(j).getInt("line_dist"));
                            transferInfo.setLine_name(ja.getJSONObject(j).getString("line_name"));
                            transferInfo.setStart_stat(ja.getJSONObject(j).getString("start_stat"));
                            transferInfo.setStats(ja.getJSONObject(j).getString("stats"));
                            transferInfoList.add(transferInfo);
                        }

                    }
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

    @Action("stationInfo")
    public String  stationInfo() throws Exception{
        String apiUrl="http://api.36wu.com/Bus/GetStationInfo?city=%E5%8C%97%E4%BA%AC&station=%E5%A4%A9%E5%AE%89%E9%97%A8";
        HttpEntity httpEntity=null;
        try {
            HttpGet httpGet=new HttpGet(apiUrl);

            HttpResponse httpResponse =new DefaultHttpClient().execute(httpGet);
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode== HttpStatus.OK.value()){
                httpEntity=httpResponse.getEntity();
                JSONObject jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
                if(jsonObject.getInt("status")==200){
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    stationInfoList=new ArrayList<StationInfo>();
                    JSONObject jb=null;
                    stationInfo=new StationInfo();
                    for(int i=0;i<jsonArray.length();i++){
                        jb=jsonArray.getJSONObject(i);
                         stationInfo.setLineName(jb.getString("line_names"));
                         stationInfo.setStationName(jb.getString("name"));
                         stationInfo.setStationXy(jb.getString("xy"));
                        stationInfoList.add(stationInfo);
                    }
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

    private String city;

    private String line;

    private String station;

    private String start;

    private String end;

    private List<LineInfo> lineInfoList;

    private List<StationInfo> stationInfoList;

    private List<TransferInfo> transferInfoList;

    private LineInfo lineInfo;

    private StationInfo stationInfo;

    private TransferInfo transferInfo;

    public List<LineInfo> getLineInfoList() {
        return lineInfoList;
    }

    public void setLineInfoList(List<LineInfo> lineInfoList) {
        this.lineInfoList = lineInfoList;
    }

    public List<StationInfo> getStationInfoList() {
        return stationInfoList;
    }

    public void setStationInfoList(List<StationInfo> stationInfoList) {
        this.stationInfoList = stationInfoList;
    }

    public List<TransferInfo> getTransferInfoList() {
        return transferInfoList;
    }

    public void setTransferInfoList(List<TransferInfo> transferInfoList) {
        this.transferInfoList = transferInfoList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
