package org.myself.mobile.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-5
 * Time: 下午1:24
 * To change this template use File | Settings | File Templates.
 */
public class BaseAction extends ActionSupport implements Preparable{
    @Override
    public void prepare() throws Exception{

    }
    @Override
    public String execute() throws Exception{
        return SUCCESS;
    }
}
