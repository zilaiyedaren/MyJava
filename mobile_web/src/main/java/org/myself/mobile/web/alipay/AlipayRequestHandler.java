package org.myself.mobile.web.alipay;

import org.myself.mobile.web.alipay.config.AlipayConfig;
import org.myself.mobile.web.alipay.util.AlipaySubmit;
import org.myself.mobile.web.alipay.util.UtilDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 */
public class AlipayRequestHandler extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig){
        System.out.println("向支付宝发送请求");
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
            doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
//        response.sendRedirect("http://www.baidu.com");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
//        ----------------------------------------------
        //支付宝网关地址
        String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
        ////////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////
        //返回格式
        String format = "xml";
        //必填，不需要修改
        //返回格式
        String v = "2.0";
        //必填，不需要修改
        //请求号
        String req_id = UtilDate.getOrderNum();
        //必填，须保证每次请求都是唯一


        //req_data详细信息
        //服务器异步通知页面路径
        String notify_url = "http://10.200.105.186:8022/mobile_web/getCallBackUrl.do";
        //需http://格式的完整路径，不能加?id=123这类自定义参数

        //页面跳转同步通知页面路径
        String call_back_url = "http://10.200.105.186:8022/mobile_web/getNotifyUrl.do";
        //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

        //操作中断返回地址
        String merchant_url = "http://10.200.105.186:8022/mobile_web/alipay/pay_index.dhtml";
        //用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

        //卖家支付宝帐户
        String seller_email = new String(request.getParameter("WIDseller_email").getBytes("ISO-8859-1"),"UTF-8");
        //必填

        //商户订单号
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //商户网站订单系统中唯一订单号，必填

        //订单名称
//        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        String subject =request.getParameter("WIDsubject");
        //必填

        //付款金额
        String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
        //必填
        //请求业务参数详细
        String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url +
                "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>"
                + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" +
                subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url +
                "</merchant_url></direct_trade_create_req>";

        //把请求参数打包成数组
        Map<String, String> sParaTempToken = new HashMap<String, String>();
        sParaTempToken.put("service", "alipay.wap.trade.create.direct");
        sParaTempToken.put("partner", AlipayConfig.partner);
        sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
        sParaTempToken.put("sec_id", AlipayConfig.sign_type);
        sParaTempToken.put("format", format);
        sParaTempToken.put("v", v);
        sParaTempToken.put("req_id", req_id);
        sParaTempToken.put("req_data", req_dataToken);
        String request_token = null;

        try {
            //建立请求
            String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
            //URLDECODE返回的信息
            sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, AlipayConfig.input_charset);
            //获取token
            request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("request_token:"+request_token);

        ////////////////////////////////////根据授权码token调用交易接口 alipay.wap.auth.authAndExecute//////////////////////////////////////

        //业务详细
        String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
        //必填

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("sec_id", AlipayConfig.sign_type);
        sParaTemp.put("format", format);
        sParaTemp.put("v", v);
        sParaTemp.put("req_data", req_data);
        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
        System.out.println("sHtmlText:"+sHtmlText);
    }
}
