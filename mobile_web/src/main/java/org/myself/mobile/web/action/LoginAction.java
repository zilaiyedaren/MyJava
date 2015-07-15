package org.myself.mobile.web.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.myself.mobile.web.action.base.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-13
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction extends BaseAction {
//    @Action("login")
    public String login(){
        System.out.println("login");
        return SUCCESS;
    }
//
//    @Action(value="loginSubmit" ,results = {@Result(name="index",type = "redirectAction", location = "index"),
//                                               @Result(name="success",type="redirectAction",location="login")})
//    @Action(value="loginSubmit",results ={@Result(type="redirectAction",location="index")})
    public String loginSubmit(){

            if (isInvalid(getUser_name()))
                return INPUT;
            if (isInvalid(getUser_pass()))
                return INPUT;

            if(this.getUser_name().equals("wh") && this.getUser_pass().equals("123")){
                ActionContext.getContext().getSession().put("username",getUser_name());
                ActionContext.getContext().getSession().put("password",getUser_pass());
                return "index";
            }

            return SUCCESS;
    }
//    @Action("show")
   public String show(){
       System.out.println("show");
       return SUCCESS;
   }
    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }
    private String user_name;
    private String user_pass;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
