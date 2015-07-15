package org.myself.mobile.web.action.wechat;

import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-30
 * Time: 下午5:46
 * To change this template use File | Settings | File Templates.
 */
public class ConfigTestAction extends BaseAction{
     @Action("wechat_config")
    public String wechatConfig(){
         return SUCCESS;
     }
}
