import org.myself.web.spring.daoimpl.IUsersDaoImpl;
import org.myself.web.spring.vo.TUsers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-7-4
 * Time: 下午7:05
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    @org.junit.Test
    public void test() throws Exception{
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUsersDaoImpl iUsersDao=(IUsersDaoImpl)factory.getBean("userDao");
        TUsers tUsers=iUsersDao.findByUsername("wh");

    }
}
