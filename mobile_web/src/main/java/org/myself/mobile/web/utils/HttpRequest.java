package org.myself.mobile.web.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-30
 * Time: 下午7:25
 * To change this template use File | Settings | File Templates.
 */
public class HttpRequest {

    public static JSONObject Request(String apiUrl,String method) throws Exception{
        if(apiUrl.equalsIgnoreCase("")){
            return null;
        }
        HttpEntity httpEntity=null;
        HttpResponse httpResponse;
        JSONObject jsonObject=null;
        try {
            if(method.equalsIgnoreCase("post")){
                HttpPost httpPost=new HttpPost(apiUrl);
                httpResponse=new DefaultHttpClient().execute(httpPost);
            }else{
                HttpGet httpGet =new HttpGet(apiUrl);
                httpResponse=new DefaultHttpClient().execute(httpGet);
                int statusCode=httpResponse.getStatusLine().getStatusCode();
                if(statusCode== HttpStatus.OK.value()){
                    httpEntity=httpResponse.getEntity();
                    jsonObject=new JSONObject(JsonStrReader.read(httpEntity));
//                    jsonObject=JSONObject.fromObject(JsonStrReader.read(httpEntity));  //将字符串转为json
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            EntityUtils.consume(httpEntity);
        }

        return jsonObject;
    }



    public static JSONObject beanToJson(Object o){ //bean转为json
        return null;
    }

    public static Object jsonToBean(JSONObject jsonObject){  //把JSON字符串转换为JAVA 对象
//        JSONObject jsonobject = JSONObject.fromObject(str);

        return null;
    }
    public static List<Object> jsonListToBean(JSONArray jsonArray,Object o){   //jsonList转为 beanList
        List<Object> list = null;

        return null;
    }
}
