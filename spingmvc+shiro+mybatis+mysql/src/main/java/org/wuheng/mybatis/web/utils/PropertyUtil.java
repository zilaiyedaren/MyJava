package org.wuheng.mybatis.web.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
public class PropertyUtil {
    private static final String API_CONFIG_FILENAME="config/development/config.properties";
    private static Properties properties;

    private synchronized static void loadProperties(){
        if(properties==null){
            properties=new Properties();
            try {
                properties.load(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(API_CONFIG_FILENAME));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public static String getProperty(String name){
        loadProperties();
        String value=properties.getProperty(name);
        return value;
    }
}
