package org.myself.mobile.web.action.about;

import com.caucho.hessian.client.HessianProxyFactory;
import com.derbysoft.dhotelier.ws.DHotelierHessianService;
import com.derbysoft.dhotelier.ws.model.WSHotelRequest;
import com.derbysoft.dhotelier.ws.model.WSHotelResponse;
import com.opensymphony.xwork2.ActionContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;
import org.myself.mobile.web.utils.Constants;

import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-5
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class AboutAction extends BaseAction {
    @Action("about_us")
    public String aboutUs(){
         return SUCCESS;
    }
}
