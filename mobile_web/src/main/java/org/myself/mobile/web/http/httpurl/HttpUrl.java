package org.myself.mobile.web.http.httpurl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-4
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public class HttpUrl {

    private static  final String GET_URL="http://api.yi18.net/drug/list";

    private static final String POST_URL="http://api.yi18.net/drug/list";

    // 拼凑get请求的URL字串，需使用URLEncoder.encode对特殊和不可见字符进行编码
    public static void get() throws Exception{
        String getURL = GET_URL + "?id=" + URLEncoder.encode("1", "utf-8");
        URL getUrl = new URL(getURL);
    //根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里的URL是一个http，因此它实际上返回的是HttpURLConnection
        HttpURLConnection httpURLConnection=(HttpURLConnection)getUrl.openConnection();
        // 建立与服务器的连接，并未发送数据
        httpURLConnection.connect();
        // 发送数据到服务器并使用Reader读取返回的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String str;
        while((str=bufferedReader.readLine())!=null){
               System.out.println(str);
        }
        bufferedReader.close();
        // 断开连接
        httpURLConnection.disconnect();
    }

    public static void post() throws Exception{
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(POST_URL);
        // 打开连接
        HttpURLConnection httpURLConnection = (HttpURLConnection) postUrl.openConnection();
        //打开读写属性，默认均为false
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        // 设置请求方式，默认为GET
        httpURLConnection.setRequestMethod("POST");
        // Post 请求不能使用缓存
        httpURLConnection.setUseCaches(false);

        // URLConnection.setFollowRedirects是static 函数，作用于所有的URLConnection对象。
        // connection.setFollowRedirects(true);
        //URLConnection.setInstanceFollowRedirects 是成员函数，仅作用于当前函数
//        httpURLConnection.setInstanceFollowRedirects(true);
        // 配置连接的Content-type，配置为application/x- www-form-urlencoded的意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，
        // 要注意的是connection.getOutputStream()会隐含的进行调用 connect()，所以这里可以省略
        //connection.connect();
        DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
//        //正文内容其实跟get的URL中'?'后的参数字符串一致
        String content = "id= "+URLEncoder.encode("1", "utf-8");   //参数
        // DataOutputStream.writeBytes将字符串中的16位的 unicode字符以8位的字符形式写道流里面
        out.writeBytes(content);
        out.flush();
        out.close(); // flush and close
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String str;
        while ((str=bufferedReader.readLine())!=null){
            System.out.println(str);
        }
        bufferedReader.close();
    }
}
