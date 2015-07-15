package org.myself.mobile.web.http.httpinvoker;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-4
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
public class ServiceImpl implements IService {

        @Override
        public String getString(String msg) {
            return "hello : " + msg;
        }
        @Override
        public Map<String, Object> getMap(String map) {
            Map<String, Object> testMap = new HashMap<String, Object>();
            testMap.put("m1", 1);
            testMap.put("m2", "2");
            List<String> list = new ArrayList<String>();
            list.add("213");
            list.add("456");
            testMap.put("m3", list);
            testMap.put(map, map);
            System.out.println("远程调用:" + map);
            return testMap;
        }
        /* (non-Javadoc)
         * @see test.IService#getCar()
         */
        @Override
        public Car getCar() {
            Car c = new Car();
            c.setName("httpinvoker-car");
            c.setCapacity(100);
            return c;
        }
}
