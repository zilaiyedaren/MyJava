package org.myself.mobile.web.http.https;

import weibo4j.org.json.JSONObject;
import weibo4j.org.json.XML;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-27
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public interface IHttpConn {
     public JSONObject httpGet(String url);

//     public  JSONObject httpPost(String url, String params);

     public  JSONObject httpsPost(String url, String params);

     public  JSONObject httpsGet(String url);
}
