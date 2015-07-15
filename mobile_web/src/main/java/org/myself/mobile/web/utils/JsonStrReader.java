package org.myself.mobile.web.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class JsonStrReader {
    private static Logger logger = LoggerFactory.getLogger(JsonStrReader.class);

    private static final String CHAR_ENCODING = "UTF-8";

    public static String read(HttpEntity httpEntity) {
        StringBuilder sb = new StringBuilder(256);
        try {
            DataInputStream inStream = new DataInputStream(httpEntity.getContent());

            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            inStream.close();
        } catch (Exception e) {
            logger.error("Read jsonStr failed due to [{}]", e.getMessage(), e);
        }
        return sb.toString();
    }

    public static <T> T jsonGetObject(HttpEntity httpEntity, Class<T> clazz) {
        DataInputStream inStream = null;
        try {
            inStream = new DataInputStream(httpEntity.getContent());
            return (new Gson()).fromJson(new InputStreamReader(inStream, CHAR_ENCODING), clazz);

            /*BufferedReader reader=new BufferedReader(new InputStreamReader(stream,CHAR_ENCODING));
               StringBuffer sb=new StringBuffer();
               for(char[] buf=new char[1024];;)
               {
                   int len=reader.read(buf);
                   if(len==-1) break;
                   sb.append(buf,0,len);
               }*/
//			InputStreamReader reader=new InputStreamReader(stream,CHAR_ENCODING);
//			StringBuffer sb=new StringBuffer();
//
//			for(int ch;(ch=reader.read())!=-1;sb.append((char)ch));
            /*{
                   int ch=reader.read();
                   if(ch==-1) break;
                   sb.append(ch);
               }*/

            ////
//			System.out.println("---post data--"+sb.toString());

//			return (new Gson()).fromJson(sb.toString(),clazz);

            //reader=new InputStreamReader(ServletActionContext.getRequest().getInputStream(),CHAR_ENCODING);
            //T rlt=(new Gson()).fromJson(reader,clazz);
            //return rlt;
            //return (new Gson()).fromJson(reader,clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inStream != null) inStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T jsonGetObject(InputStream stream, Class<T> clazz) {
        DataInputStream inStream = null;
        try {
            inStream = new DataInputStream(stream);
            return (new Gson()).fromJson(new InputStreamReader(inStream, CHAR_ENCODING), clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inStream != null) inStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void jsonWriteObject(Object obj) {
        PrintWriter writer = null;
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding(CHAR_ENCODING);
            writer = response.getWriter();
            writer.println((new Gson()).toJson(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getClientIP() {
        return ServletActionContext.getRequest().getRemoteAddr();
    }
}
