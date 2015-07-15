package org.myself.mobile.web.action.contact;


import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;


/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-5
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class ContactAction extends BaseAction {
    @Action("contact")
    public String contact(){
        return SUCCESS;
    }
}
