package org.wuheng.mybatis.web.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
public class ServiceFactory implements ApplicationContextAware {
//    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/summercoll-spring/applicationContext.xml");
//    private static ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/summercoll-spring/applicationContext.xml");
    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz){
           checkApplicationContext();
        if(applicationContext==null){
            return null;
        }
        return (T)applicationContext.getBean(clazz);
    }
    public static ApplicationContext getApplicationContext(){
        checkApplicationContext();
        return applicationContext;
    }
    private static void checkApplicationContext(){
        if(applicationContext==null){
            throw new IllegalStateException(
                    "applicaitonContext未注入,请在applicationContext.xml中定义ServiceFactory"
            );
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServiceFactory.applicationContext=applicationContext;
    }
}
