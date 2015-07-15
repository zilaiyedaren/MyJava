package org.myself.mobile.web.http.httpinvoker;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-4
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public interface IService {

        /**
         * @param msg
         * @return
         */
        public String getString(String msg);

        /**
         * @param map
         * @return
         */
        public Map<String, Object> getMap(String map);

        /**
         * @return
         */
        public Car getCar();

}
