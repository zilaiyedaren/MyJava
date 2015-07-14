package org.wuheng.mybatis.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.service.ServiceFactory;
import org.wuheng.mybatis.web.slient.view.ImageView;


import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
@Controller("captchaController")
@RequestMapping("/slient/views/captcha")
public class CaptchaController{
    @Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "/slient/views/captcha",method = RequestMethod.GET)
    public @ResponseBody ModelAndView captcha(){
//        Producer captchaProducer= ServiceFactory.getBean(Producer.class);
        //创建验证码文本
        String captchaText=captchaProducer.createText();
        SecurityUtils.getSubject().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,captchaText);
        BufferedImage bufferedImage=captchaProducer.createImage(captchaText);
        ImageView imageView=new ImageView(bufferedImage);

        return new ModelAndView(imageView);
    }
}
//public class CaptchaController extends AbstractController {
//    @Override
//    protected ModelAndView handleRequestInternal(HttpServletRequest request,
//                                                 HttpServletResponse response) throws Exception {
//
//        Producer captchaProducer= ServiceFactory.getBean(Producer.class);
//        //创建验证码文本
//        String captchaText=captchaProducer.createText();
//        SecurityUtils.getSubject().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,captchaText);
//        BufferedImage bufferedImage=captchaProducer.createImage(captchaText);
//        ImageView imageView=new ImageView(bufferedImage);
//
//        return new ModelAndView(imageView);  //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
