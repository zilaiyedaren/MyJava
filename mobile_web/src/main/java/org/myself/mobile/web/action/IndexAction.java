package org.myself.mobile.web.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-5
 * Time: 下午1:23
 * To change this template use File | Settings | File Templates.
 */
public class IndexAction extends BaseAction {
   @Action("index")
   public String index() throws Exception{
      if(lan!=null){
          ActionContext.getContext().getSession().put("WW_TRANS_I18N_LOCALE",this.getLocale());
          ActionContext.getContext().getSession().put("locale",lan);
          ActionContext.getContext().setLocale(this.getLocale());
      }else{
          ActionContext.getContext().setLocale((Locale) ActionContext.getContext().getSession().get("WW_TRANS_I18N_LOCALE"));
      }
       if(os!=null){
           ActionContext.getContext().getSession().put("os",os);
       }
       return SUCCESS;
   }
    public Locale getLocale(){
        if ("en_US".equalsIgnoreCase(lan)) {
            return new Locale("en", "US");
        }
        return new Locale("zh", "CN");
    }
    private String lan;
    private String os;

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}

