package org.myself.mobile.web.alipay;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class AlipayNotifyHandler extends HttpServlet {
   @Override
    public void init(ServletConfig servletConfig){
       System.out.println("支付宝异步服务器调用");
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException{
          doPost(request,response);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        System.out.println("post");
        //获取支付宝异步POST过来反馈信息
    }

}
