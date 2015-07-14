package org.wuheng.mybatis.web.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */

/**
 * 类型转换
 */
public class JsonUtil {
    private static ObjectMapper om=new ObjectMapper();

    public static String getJsonStr(Object object){
        try {
            return om.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return "";
        }
    }

    public static <T> T getObjectClass(String json,Class<T> clazz){
        try {
            return (T) om.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}
