package org.myself.mobile.web.http.webservice;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.myself.mobile.web.action.base.BaseAction;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-9
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
/*
* HTTP SOAP请求webservice服务 (跨平台首选)
* */
public class WebserviceSoapAction extends BaseAction {

//    @Action("plane")
//    public String webserviceSoap() throws Exception{
//       /*
//       *  通过dom4j对服务器端返回的XML进行解析
//       * */
//          Document document=null;
//          SAXReader saxReader=new SAXReader();
//          String s="";
//          Map map=new HashMap();
//          map.put("design","http://WebXml.com.cn/");
//          saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
//           String city ="咸宁";
//          try {
//              InputStream inputStream=getSoapInputStream(city);//获取输入流
//              document=saxReader.read(inputStream);//将输入流转化为document
////              String str=document.asXML();
//          } catch (Exception e) {
//              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//          }
//          List nodes =document.selectNodes("//design:string");
//          for(Iterator it =nodes.iterator();it.hasNext();) {
//              Element element=(Element) it.next();
//              String text=element.getText();
//              System.out.println("输出："+text);
//              s=s+element.getText()+"\n";
//          }
////        System.out.println("输出："+s);
//          return SUCCESS;
//      }
//
//        /* 获取soap请求头，并替换其中的标志符号为用户的输入符号
//        *@return 用户将要发送给服务器的soap请求
//        */
//    private String getSoapRequest(String city){
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
//                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
//                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//                + "<soap:Body>    <getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\">"
//                + "<theCityName>" + city
//                + "</theCityName> " +
//                "</getWeatherbyCityName>"
//                + "</soap:Body></soap:Envelope>");
//        return stringBuilder.toString();
//    }
//    private InputStream getSoapInputStream(String city) throws Exception{
//
//        try {
//            String soap=getSoapRequest(city);
//            if(soap == null){
//                return null;
//            }
//            URL url=new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");
//            URLConnection connection=url.openConnection();
//            connection.setUseCaches(false);
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Length", Integer.toString(soap.length()));
//            connection.setRequestProperty("Content-Type","text/xml;charset=utf-8");
//            connection.setRequestProperty("SOAPAction","http://WebXml.com.cn/getWeatherbyCityName");  //请求方法
//            OutputStream outputStream=connection.getOutputStream();
//            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"utf-8");
//
//            outputStreamWriter.write(soap);
//            outputStreamWriter.flush();
//            outputStreamWriter.close();
//            InputStream inputStream=connection.getInputStream();
//
////            System.out.println(inputStream.toString());
//            return inputStream;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            return null;
//        }
//
//    }

//    @Action("webservice")
//    public String webservice(){
//        return SUCCESS;
//    }
//    @Action("plane")
    public String webserviceSoap() throws Exception{
       /*
       *  通过dom4j对服务器端返回的XML进行解析
       * */
        Document document=null;
        SAXReader saxReader=new SAXReader();
        String s="";
        Map map=new HashMap();
        map.put("design","http://WebXml.com.cn/");
        saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
        try {
            InputStream inputStream=getSoapInputStream();//获取输入流
            document=saxReader.read(inputStream);//将输入流转化为document
            String str=document.asXML();//将字符流转为XML
            System.out.println("输出："+str);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        List nodes =document.selectNodes("//diffgr:diffgram");
//        从根节点选取
//        从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置
        for(Iterator it =nodes.iterator();it.hasNext();) {
            Element element=(Element) it.next();
            String text=element.getText();
            System.out.println("输出："+text);
            s=s+element.getText()+"\n";
        }
        return SUCCESS;
    }

    /* 获取soap请求头，并替换其中的标志符号为用户的输入符号
    *@return 用户将要发送给服务器的soap请求
    */
    private String getSoapRequest(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>    <getDomesticAirlinesTime xmlns=\"http://WebXml.com.cn/\">"
               +"<startCity>上海</startCity><lastCity>武汉</lastCity><theDate>2014-11-11</theDate><userID></userID>"+
                "</getDomesticAirlinesTime>"
                + "</soap:Body></soap:Envelope>");
        return stringBuilder.toString();
    }
    private InputStream getSoapInputStream() throws Exception{
        try {
            String soap=getSoapRequest();
            if(soap == null){
                return null;
            }
            URL url=new URL("http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx");
            URLConnection connection=url.openConnection();
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            connection.setRequestProperty("Content-Type","text/xml;charset=utf-8");
            connection.setRequestProperty("SOAPAction","http://WebXml.com.cn/getDomesticAirlinesTime");  //请求方法
            OutputStream outputStream=connection.getOutputStream();
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"utf-8");

            outputStreamWriter.write(soap);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            InputStream inputStream=connection.getInputStream();
            return inputStream;
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}