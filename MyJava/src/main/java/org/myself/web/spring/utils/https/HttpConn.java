package org.myself.web.spring.utils.https;

import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-27
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
public class HttpConn implements IHttpConn {
    private String line;
    @Override
    public JSONObject httpGet(String url) {
        try {
            URL getUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) getUrl.openConnection();
            // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
            conn.connect();// 取得输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//设置编码,否则中文乱码
            StringBuilder sb = new StringBuilder(256);
            while ((line= reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject httpPost(String url,String params) {
        // Post请求的url，与get不同的是不需要带参数
        try {
            URL postUrl = new URL(url);
            HttpURLConnection conn= (HttpURLConnection) postUrl.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);  // Post 请求不能使用缓存
            conn.setInstanceFollowRedirects(true);
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            out.writeBytes(params);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//设置编码,否则中文乱码
            StringBuilder sb = new StringBuilder(256);
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject httpsPost(String url,String params) {
//        String url="https://kyfw.12306.cn/otn/lcxxcx/query?
//        params=purpose_codes="+type+"&queryDate=2014-11-28&from_station="+startCity+"&to_station="+endCity;
        try {
            SSLContext sc=SSLContext.getInstance("SSL");
            sc.init(null,new TrustManager[]{new MyX509TrustManager()},new SecureRandom());
            URL postUrl=new URL(url);
            HttpsURLConnection conn=(HttpsURLConnection) postUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);  // Post 请求不能使用缓存
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.connect();
            DataOutputStream out=new DataOutputStream(conn.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));
            StringBuilder sb = new StringBuilder(256);
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
            return new JSONObject(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KeyManagementException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject httpsGet(String url) {
        JSONObject jsonObject=null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new MyHostnameVerifier());
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//设置
            StringBuilder sb = new StringBuilder(256);
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            conn.disconnect();  // 断开连接
            jsonObject= new JSONObject(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KeyManagementException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return jsonObject;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
