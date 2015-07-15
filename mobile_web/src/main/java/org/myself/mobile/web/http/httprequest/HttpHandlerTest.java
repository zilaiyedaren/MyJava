package org.myself.mobile.web.http.httprequest;

import org.myself.mobile.web.utils.HttpRequest;
import weibo4j.org.json.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-1
 * Time: 下午2:19
 * To change this template use File | Settings | File Templates.
 */
public class HttpHandlerTest extends HttpServlet   {
        //初始化参数方法 init()默认，可以重载
        @Override
    public void init(ServletConfig servletConfig) throws ServletException{
              System.out.println("HttpHandlerTest+init");
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        //doGet请求url长度有限制，安全性也不好
        //设置返回数据(文本类型) text/html HTML,text/plain  TXT,text/xml  XML,text/json  json字符串
//        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONObject object=new JSONObject();
        String apiUrl= "http://www.weather.com.cn/data/cityinfo/101010100.html";
        try {
            if( request.getQueryString()==null){
                object.put("errmsg","输入参数不正确");
                object.put("errcode","4000");
            }else{
                String name=request.getParameter("city");
                String pwd=request.getParameter("code");
                if(name==null||name.equalsIgnoreCase("")){
                    object.put("errmsg","城市为空");
                    object.put("errcode","4001");
                }else if(pwd==null||pwd.equalsIgnoreCase("")){
                    object.put("errmsg","城市代码为空");
                    object.put("errcode","4002");
                }else{
                    object=HttpRequest.Request(apiUrl,"get");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        out.append(object.toString());
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //   doGet(request,response);
        response.setContentType("utf-8");
        response.setContentType("application/json;charset=utf-8");
        ServletInputStream sis=request.getInputStream();  //以字符流的形式获取

    }
}
