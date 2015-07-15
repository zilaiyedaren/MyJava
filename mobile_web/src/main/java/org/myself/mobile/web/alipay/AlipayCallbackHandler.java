package org.myself.mobile.web.alipay;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-30
 * Time: 下午1:24
 * To change this template use File | Settings | File Templates.
 */
public class AlipayCallbackHandler extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig){
        System.out.println("支付宝异步服务器调用");
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException{
        //获取支付宝同步GET过来反馈信息

        try {
            //只能在url中带parameter或者放在session中，无法使用request.setAttribute来传递
//            response.sendRedirect("alipay/pay_result.dhtml?name=adas&psw=1213");
            //验证通过后，进行跳转
            response.sendRedirect("alipay/pay_result.dhtml");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException{
            doGet(request,response);
    }
}
