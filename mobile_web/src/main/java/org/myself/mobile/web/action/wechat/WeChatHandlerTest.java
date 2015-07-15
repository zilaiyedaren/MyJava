package org.myself.mobile.web.action.wechat;

import com.thoughtworks.xstream.XStream;
import org.myself.mobile.web.action.wechat.gson.MessageProcessingHandlerImpl;
import org.myself.mobile.web.action.wechat.gson.bean.Articles;
import org.myself.mobile.web.action.wechat.gson.bean.InMessage;
import org.myself.mobile.web.action.wechat.gson.bean.Music;
import org.myself.mobile.web.action.wechat.gson.bean.OutMessage;
import org.myself.mobile.web.action.wechat.gson.inf.MessageProcessingHandler;
import org.myself.mobile.web.action.wechat.gson.util.Tools;
import org.myself.mobile.web.action.wechat.gson.util.XStreamFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WeChatHandlerTest extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(WeChatHandlerTest.class);

    private String _token = "shundejunhao";

    private MessageProcessingHandlerImpl messageProcessingHandlerImpl;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
//        ServletContext servletContext = servletConfig.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.
//                getWebApplicationContext(servletContext);
//        AutowireCapableBeanFactory autowireCapableBeanFactory =
//                webApplicationContext.getAutowireCapableBeanFactory();
//        autowireCapableBeanFactory.configureBean(this, "messageProcessingHandlerImpl");
        messageProcessingHandlerImpl = new MessageProcessingHandlerImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            logger.error("you are coming");
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");//
            // 验证
            if (Tools.checkSignature(_token, signature, timestamp, nonce)) {
                response.getWriter().write(echostr);
            }
//            String jsonString = "";
//            if (StringUtils.isNotBlank(request.getParameter("signature"))){
//                jsonString = request.getParameter("echostr");
//            }
//            OutputStream os = response.getOutputStream();
//            os.write(jsonString.getBytes("GBK"));
//            os.flush();
//            os.close();
        } catch (Exception e) {
            logger.error("*** error when validating wechat request. [{}]", e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        OutMessage oms = new OutMessage();
        ServletInputStream in = request.getInputStream();
        // 转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);
        xs.alias("xml", InMessage.class);
        String xmlMsg = Tools.inputStream2String(in);
        InMessage msg = (InMessage) xs.fromXML(xmlMsg);
        try {
            // 取得消息类型
            String type = msg.getMsgType();
            // 针对不同类型消息进行处理
            if (type.equals(MessageProcessingHandler.MSG_TYPE_TEXT)) {
                oms = messageProcessingHandlerImpl.textTypeMsg(msg);
            }
//            } else if (type.equals(MessageProcessingHandler.MSG_TYPE_LOCATION)) {
//                oms = processingHandler.locationTypeMsg(msg);
//            } else if (type.equals(MessageProcessingHandler.MSG_TYPE_LINK)) {
//                oms = processingHandler.linkTypeMsg(msg);
            else if (type.equals(MessageProcessingHandler.MSG_TYPE_IMAGE)) {
                oms = messageProcessingHandlerImpl.imageTypeMsg(msg);
//            } else if (type.equals(MessageProcessingHandler.MSG_TYPE_VOICE)) {
//                oms = processingHandler.voiceTypeMsg(msg);
            } else if (type.equals(MessageProcessingHandler.MSG_TYPE_EVENT)) {
                oms = messageProcessingHandlerImpl.eventTypeMsg(msg);
            } else if (type.equals(MessageProcessingHandler.MSG_TYPE_VOICE)) {
                oms = messageProcessingHandlerImpl.voiceTypeMsg(msg);
            }
            if (oms == null) {
                oms = new OutMessage();
                oms.setContent("系统错误!");
            }
        } catch (Exception e) {
            logger.error("response wechat error", e);
            oms.setContent("系统错误！");
        }
        // 把发送发送对象转换为xml输出
        xs = XStreamFactory.init(false);
        xs.alias("xml", OutMessage.class);
        xs.alias("item", Articles.class);
        xs.alias("Music", Music.class);
        System.out.println(oms.toString());
        logger.error(xs.toXML(oms));
        xs.toXML(oms, response.getWriter());

    }
}
