package org.myself.mobile.web.http.httphessian;

import com.caucho.hessian.server.HessianServlet;
import weibo4j.org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-2
 * Time: 下午6:10
 * To change this template use File | Settings | File Templates.
 */
//接口实现类 (hessian 序列号和反序列化的实现机制， hessian 序列化的速度很快，而且序列化后的字节数也较其他技术少。)
public class HessianImplements extends HessianServlet implements HessianInterface {
    private static final long serialVersionUID = 7619464791326509626L;//需要进行序列化
    //实现接口中的方法
    public WeatherInfo request(){
        JSONObject jsonObject=new JSONObject();
        String apiUrl="http://www.weather.com.cn/data/cityinfo/101010100.html";
        WeatherInfo weatherInfo=new WeatherInfo();
        try {
            weatherInfo.setCity("上海");
            weatherInfo.setPtime("2014-11-06");
//            jsonObject= HttpRequest.Request(apiUrl, "get").getJSONObject("weatherinfo");
//            weatherInfo.setCity(jsonObject.getString("city"));
//            weatherInfo.setCityid(jsonObject.getString("cityid"));
//            weatherInfo.setTemp1(jsonObject.getString("temp1"));
//            weatherInfo.setTemp2(jsonObject.getString("temp2"));
//            weatherInfo.setPtime(jsonObject.getString("ptime"));
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return weatherInfo;
    }
//    public void init(ServletConfig servletConfig){
//          System.out.println("init");
//    }
//    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
//        //doGet请求url长度有限制，安全性也不好
//        //设置返回数据(文本类型) text/html HTML,text/plain  TXT,text/xml  XML,text/json  json字符串
////        response.setContentType("text/json");
//          System.out.println("需要进行POST请求");
//    }
////
//    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
//        //   doGet(request,response);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; chaset=utf-8");
//        PrintWriter out = response.getWriter();
//        JSONObject object=new JSONObject();
//        String apiUrl= "http://www.weather.com.cn/data/cityinfo/101010100.html";
//        try {
//            if( request.getQueryString()==null){
//                object.put("errmsg","输入参数不正确");
//                object.put("errcode","4000");
//            }else{
//                String name=request.getParameter("city");
//                String pwd=request.getParameter("code");
//                if(name==null||name.equalsIgnoreCase("")){
//                    object.put("errmsg","城市为空");
//                    object.put("errcode","4001");
//                }else if(pwd==null||pwd.equalsIgnoreCase("")){
//                    object.put("errmsg","城市代码为空");
//                    object.put("errcode","4002");
//                }else{
//                    object= HttpRequest.HttpRequest(apiUrl, "get");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        out.append(object.toString());
//
//    }
}
