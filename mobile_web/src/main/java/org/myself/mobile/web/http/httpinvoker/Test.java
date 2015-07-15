import org.springframework.context.ApplicationContext;



import org.myself.mobile.web.http.httpinvoker.Car;
import org.myself.mobile.web.http.httpinvoker.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-4
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
public class Test {
        /**
         * @param args
         */
        // ClassPathXmlApplicationContext 是读取 src 目录下的配置文件
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//    //  FileSystemXmlApplicationContext 即系统文件路径，文件的目录。
//    ApplicationContext context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");

        public static void main(String[] args) {
//            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("remote-client.xml");
            ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/java/org/myself/mobile/web/action/httpinvoker/remote-client.xml");
            IService service = (IService) applicationContext.getBean("remoteService");
            String msg = service.getString("httpinvoker");
            System.out.println(msg);

            Car c = service.getCar();
            System.out.println(c.getName());
            System.out.println(c.getCapacity());

            Map<String, Object> map = service.getMap("test");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "," + entry.getValue());
            }
        }

}
