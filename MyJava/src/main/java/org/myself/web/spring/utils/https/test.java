package org.myself.web.spring.utils.https;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-27
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */

public class test {
    public static void main(String[] args) throws Exception {
//        SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
        String type = "ADULT";
        String startCity="BJP";
        String endCity="WHN";
        String urlApi="https://kyfw.12306.cn/otn/lcxxcx/query?purpose_codes="+type+"&queryDate=2015-07-28&from_station="+startCity+"&to_station="+endCity;
//        URL console = new URL(urlApi);
//        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
//        conn.setSSLSocketFactory(sc.getSocketFactory());
//        conn.setHostnameVerifier(new MyHostnameVerifier());
//        conn.connect();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//设置
//        String str=null;
//        String s=null;
//        while ((str = reader.readLine()) != null){
//            s+=str;
//        }
//        reader.close();
//        // 断开连接
//        conn.disconnect();
        System.out.println(urlApi);
        HttpConn httpConn=new HttpConn();
        System.out.println(httpConn.httpsGet(urlApi).toString());
    }
}
