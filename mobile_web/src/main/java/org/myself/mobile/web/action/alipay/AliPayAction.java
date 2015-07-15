package org.myself.mobile.web.action.alipay;

import org.apache.struts2.convention.annotation.Action;
import org.myself.mobile.web.action.base.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午5:41
 * To change this template use File | Settings | File Templates.
 */
public class AliPayAction extends BaseAction {
    //返回支付成功页面
    @Action("pay_result")
    public String payResult(){
//        System.out.println(name);
        return SUCCESS;
    }
    @Action("pay_index")
    public String payIndex(){
        //提供参数
        return SUCCESS;
    }
    @Action("alipayapi")
    public String alipayapi(){
        //对支付宝服务器请求
        return SUCCESS;
    }
    @Action("alipay_test")
    public String alipayTest(){
        return SUCCESS;
    }

    private String name;
    private String psw;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
