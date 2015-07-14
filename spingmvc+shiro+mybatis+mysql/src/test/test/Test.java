package test;

import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.summercool.util.MD5Util;
import org.wuheng.mybatis.web.dao.entertainment.impl.FilmDaoImpl;
import org.wuheng.mybatis.web.dao.impl.UserDaoImpl;
import org.wuheng.mybatis.web.slient.pojo.User;
import org.wuheng.mybatis.web.utils.PasswordHelper;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-8
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    @org.junit.Test
    public void testDao(){
        BeanFactory factory = new ClassPathXmlApplicationContext("/summercoll-spring/dataSource-spring.xml");
        FilmDaoImpl filmDao= (FilmDaoImpl)factory.getBean("filmDao");
        filmDao.getSqlSession().delete("16");

        System.out.println();
    }
}
