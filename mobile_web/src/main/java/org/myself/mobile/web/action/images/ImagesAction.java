package org.myself.mobile.web.action.images;



import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-4
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
public class ImagesAction extends BaseAction {
    protected Logger logger= LoggerFactory.getLogger(ImagesAction.class);

    @Action("img_scan")
    public String images_scan(){
        return SUCCESS;
    }
}
