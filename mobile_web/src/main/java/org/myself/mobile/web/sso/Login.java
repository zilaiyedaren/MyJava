package org.myself.mobile.web.sso;

import java.io.*;
import java.net.URLEncoder;
import java.util.Scanner;
import java.net.Socket;
/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-11
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
//单点登录简单实现
public class Login {
    public static String getCookie(){
//        System.out.print("输入用户名：");
//        Scanner in=new Scanner(System.in);//从控制台输入
//        String userName=in.next();
//        System.out.print("输入密码：");
//        String passWord=in.next();
        String userName="王小二";
        String passWord="123456";

        try {
            // 本地认证
            String cookie = postLogin(userName, passWord);
            if (cookie == null) {
                return null;
            }
            //服务器远程认证
            String url = getLogin2(cookie);
            if (url == null) {
                return null;
            }
            // 远程访问认证
            String cookie2 = getLogin3(url.substring("http://s4.verycd.9wee.com".length()));
            System.out.println(cookie2);
            return cookie2;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static String postLogin(String userName,String passWord) throws Exception{
        Socket socket=new Socket("secure.verycd.com",80);

        try {
            StringBuilder sb=new StringBuilder();
            //设置请求头部
            sb.append("POST /signin?f=out HTTP/1.1\r\n");
            sb.append("Host: secure.verycd.com\r\n");
            sb.append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3\r\n");
            sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
            sb.append("Accept-Language: zh-cn,zh;q=0.5\r\n");
            sb.append("Accept-Encoding: gzip,deflate\r\n");
            sb.append("Accept-Charset: GB2312,utf-8;q=0.7,*;q=0.7\r\n");
            sb.append("Referer: http://secure.verycd.com/3rdServices/50hero\r\n");
            sb.append("Content-Type: application/x-www-form-urlencoded\r\n");
            String data="http%3A%2F%2Fsecure.verycd.com%2F3rdServices%2F50hero&login_submit=%E7%99%BB%E5%BD%95&username="
                    + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(passWord, "UTF-8");

            sb.append("Content-Length:" + data.getBytes("UTF-8").length + "\r\n");
            sb.append("\r\n");
            sb.append(data);

            OutputStream os=socket.getOutputStream();
            os.write(sb.toString().getBytes("UTF-8"));
            os.flush();
            InputStream is =socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8")); // 读取结果
            String line;
            StringBuilder cookieSb = new StringBuilder();
            int index;
            while ((line=reader.readLine())!=null){

                if (line.startsWith("Set-Cookie:")){
                    if (!line.contains("=deleted;")){
                        index = line.indexOf(";");
                        if (index > 12){
                            cookieSb.append(line.substring(12, index + 1));
                        }
                    }
                }else if (line.startsWith("location:")){
                    if (line.contains("error_code")){
                        return null;
                    }
                }
            }
            is.close();
            reader.close();
            return cookieSb.toString();
        }finally {
            socket.close();
        }
    }

    private static String getLogin2(String cookie) throws Exception {
        Socket socket = new Socket("secure.verycd.com", 80);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("GET /signin?ak=50hero&ru=http%3A%2F%2Fs4.verycd.9wee.com%2Fpassport.php HTTP/1.1\r\n");
            sb.append("Host: secure.verycd.com\r\n");
            sb.append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3\r\n");
            sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
            sb.append("Accept-Language: zh-cn,zh;q=0.5\r\n");
            sb.append("Accept-Encoding: gzip,deflate\r\n");
            sb.append("Accept-Charset: GB2312,utf-8;q=0.7,*;q=0.7\r\n");
            sb.append("Referer: http://secure.verycd.com/3rdServices/50hero\r\n");
            sb.append("Cookie: " + cookie + "\r\n");
            sb.append("\r\n");
            OutputStream os = socket.getOutputStream();
            os.write(sb.toString().getBytes("UTF-8"));
            os.flush();
            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8")); // 读取结果
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("location:")) {
                    return line.substring(10);
                }
            }
            is.close();
            reader.close();
            return null;
        } finally {
            socket.close();
        }
    }

    private static String getLogin3(String url) throws Exception {
        Socket socket = new Socket("s4.verycd.9wee.com", 80);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("GET " + url + " HTTP/1.1\r\n");
            sb.append("Host: s4.verycd.9wee.com\r\n");
            sb.append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3\r\n");
            sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
            sb.append("Accept-Language: zh-cn,zh;q=0.5\r\n");
            sb.append("Accept-Encoding: gzip,deflate\r\n");
            sb.append("Accept-Charset: GB2312,utf-8;q=0.7,*;q=0.7\r\n");
            sb.append("Referer: http://secure.verycd.com/3rdServices/50hero\r\n");
            sb.append("\r\n\r\n");
            OutputStream os = socket.getOutputStream();
            os.write(sb.toString().getBytes("UTF-8"));
            os.flush();
            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8")); // 读取结果
            String line;
            StringBuilder cookieSb = new StringBuilder();
            int index;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    break;
                }
                if (line.startsWith("Set-Cookie:")) {
                    index = line.indexOf(";");
                    if (index > 12) {
                        cookieSb.append(line.substring(12, index + 1));
                    }
                }
            }
            is.close();
            reader.close();
            return cookieSb.toString();
        } finally {
            socket.close();
        }
    }
}
