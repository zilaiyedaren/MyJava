package org.myself.mobile.web.action.wechat.gson;


import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.myself.mobile.web.action.wechat.gson.bean.Articles;
import org.myself.mobile.web.action.wechat.gson.bean.InMessage;
import org.myself.mobile.web.action.wechat.gson.bean.OutMessage;
import org.myself.mobile.web.action.wechat.gson.inf.MessageProcessingHandler;
import org.myself.mobile.web.action.wechat.gson.util.Tools;
import org.myself.mobile.web.action.wechat.gson.util.XStreamFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * 请求拦截
 * 
 * @author GodSon
 * 
 */
public class WeChatFilter implements Filter {

	private final Logger logger = Logger.getLogger(WeChatFilter.class);
	private String _token;
	private String conf = "classPath:wechat.properties";
	private String defaultHandler = "com.gson.inf.DefaultMessageProcessingHandlerImpl";
	private Properties p;

	@Override
	public void destroy() {
		logger.info("WeChatFilter已经销毁");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Boolean isGet = request.getMethod().equals("GET");

		String path = request.getServletPath();
		String pathInfo = path.substring(path.lastIndexOf("/"));

		if (pathInfo == null) {
			response.getWriter().write("error");
		} else {
			_token = pathInfo.substring(1);
			if (isGet) {
				doGet(request, response);
			} else {
				doPost(request, response);
			}
		}
	}

	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");

		OutMessage oms = new OutMessage();
		ServletInputStream in = request.getInputStream();
		// 转换微信post过来的xml内容
		XStream xs = XStreamFactory.init(false);
		xs.alias("xml", InMessage.class);
		String xmlMsg = Tools.inputStream2String(in);
		InMessage msg = (InMessage) xs.fromXML(xmlMsg);
		// 获取自定消息处理器，如果自定义处理器则使用默认处理器。
		String handler = p.getProperty("MessageProcessingHandlerImpl");
		if (handler == null)
			handler = defaultHandler;

		try {
			// 加载处理器
			Class<?> clazz = Class.forName(handler);
			MessageProcessingHandler processingHandler = (MessageProcessingHandler) clazz.newInstance();
			// 取得消息类型
			String type = msg.getMsgType();
			// 针对不同类型消息进行处理
			if (type.equals(MessageProcessingHandler.MSG_TYPE_TEXT)) {
				oms = processingHandler.textTypeMsg(msg);
			} else if (type.equals(MessageProcessingHandler.MSG_TYPE_LOCATION)) {
				oms = processingHandler.locationTypeMsg(msg);
			} else if (type.equals(MessageProcessingHandler.MSG_TYPE_LINK)) {
				oms = processingHandler.linkTypeMsg(msg);
			} else if (type.equals(MessageProcessingHandler.MSG_TYPE_IMAGE)) {
				oms = processingHandler.imageTypeMsg(msg);
			} else if (type.equals(MessageProcessingHandler.MSG_TYPE_VOICE)) {
				oms = processingHandler.voiceTypeMsg(msg);
			} else if (type.equals(MessageProcessingHandler.MSG_TYPE_EVENT)) {
				oms = processingHandler.eventTypeMsg(msg);
			}
			if (oms == null) {
				oms = new OutMessage();
				oms.setContent("系统错误!");
			}
			// 设置发送信息
			oms.setCreateTime(new Date().getTime());
			oms.setToUserName(msg.getFromUserName());
			oms.setFromUserName(msg.getToUserName());
		} catch (ClassNotFoundException e) {
			logger.error("没有找到" + handler + "类", e);
			oms.setContent("系统错误！");
		} catch (InstantiationException e) {
			logger.error(e);
			oms.setContent("系统错误！");
		} catch (IllegalAccessException e) {
			logger.error(e);
			oms.setContent("系统错误！");
		}

		// 把发送发送对象转换为xml输出
		xs = XStreamFactory.init(false);
		xs.alias("xml", OutMessage.class);
		xs.alias("item", Articles.class);
		xs.toXML(oms, response.getWriter());
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");//
		// 验证
		if (Tools.checkSignature(_token, signature, timestamp, nonce)) {
			response.getWriter().write(echostr);
		}
	}

	/**
	 * 启动的时候加载wechat.properties配置 可以在过滤器配置wechat.properties路径
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		String cf = config.getInitParameter("conf");
		if (cf != null) {
			conf = cf;
		}
		String classPath = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
		conf = conf.replace("classPath:", classPath);
		p = new Properties();
		File pfile = new File(conf);
		if (pfile.exists()) {
			try {
				p.load(new FileInputStream(pfile));
			} catch (FileNotFoundException e) {
				logger.error("未找到wechat.properties", e);
			} catch (IOException e) {
				logger.error("wechat.properties读取异常", e);
			}
		}
		logger.info("WeChatFilter已经启动！");
	}

}
