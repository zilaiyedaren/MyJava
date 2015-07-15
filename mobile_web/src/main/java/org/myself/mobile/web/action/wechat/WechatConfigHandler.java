package org.myself.mobile.web.action.wechat;

import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-30
 * Time: 下午7:28
 * To change this template use File | Settings | File Templates.
 */
public class WechatConfigHandler extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig){
        System.out.println("as");
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");
        getAllParamName(request);
        PrintWriter out=null;
        JSONObject object=new JSONObject();
        try {
            String[] a={"asd","12321"};
            try {
                CreateMenu.main(a);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            out = response.getWriter();
            object.put("msg","success");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        out.append(object.toString());
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response){
           doPost(request,response);
    }
//    获取request里的所有参数及参数名（参数名自动获取）
    public String getAllParamName(HttpServletRequest request){
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
        return null;
    }
    //    获取request里的所有参数及参数名（参数名自动获取）
    public String getAllParamMap(HttpServletRequest request){
        Map map=request.getParameterMap();
        Set keSet=map.entrySet();
        for(Iterator itr=keSet.iterator();itr.hasNext();){
            Map.Entry me=(Map.Entry)itr.next();
            Object ok=me.getKey();
            Object ov=me.getValue();
            String[] value=new String[1];
            if(ov instanceof String[]){
                value=(String[])ov;
            }else{
                value[0]=ov.toString();
            }
            for(int k=0;k<value.length;k++){
                System.out.println(ok+"="+value[k]);
            }
        }
        return null;
    }

}
