package org.wuheng.mybatis.web.slient.widgets;

import org.summercool.web.servlet.view.freemarker.FreeMarkerWidget;
import org.wuheng.mybatis.web.slient.config.cookie.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public class HeaderWidgets implements FreeMarkerWidget {
    @Override
    public void referenceData(HttpServletRequest request, Map<String, Object> model) {
          model.put("userName", CookieUtils.getUserName(request));
    }
}
