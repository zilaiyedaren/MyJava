package org.wuheng.mybatis.web.controller;

import com.google.code.kaptcha.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.summercool.web.views.string.StringView;
import org.wuheng.mybatis.web.formBean.LoginForm;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午7:32
 * To change this template use File | Settings | File Templates.
 */

@Controller("loginController")
@SuppressWarnings("login")
@RequestMapping("/slient/views/")
public class LoginController{
    /**
     * 接受一个LoginFrom对象为参数
     * @param loginForm
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody ModelAndView login(LoginForm loginForm){
        if(StringUtils.isEmpty(loginForm.getLoginName()) || StringUtils.isEmpty(loginForm.getPassword())){
            return new ModelAndView("redirect:/slient/views/index");
        }
        StringView stringView=null;
        String result="";
        //subject来进行认证和授权，而Subject又委托给SecurityManager
        Subject subject=SecurityUtils.getSubject();
        String sessionCaptcha=String.valueOf(subject.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
        if(!loginForm.getCaptcha().equals(sessionCaptcha)){
            result="{\"message\":\"验证码错误！\",\"success\":false}";
            stringView=new StringView(result);
            return new ModelAndView(stringView);
        }

        UsernamePasswordToken token=new UsernamePasswordToken(loginForm.getLoginName(),loginForm.getPassword());
        token.setRememberMe(true);

        try {
            //对用户输入的用户名和密码进行验证，最终进入UserRealm进行验证
            subject.login(token);

            subject.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
            result="{\"message\":\"登录成功\",\"success\":true}";
        }catch (LockedAccountException e){
            result = "{\"message\":\"账户被锁定，请联系管理员！\",\"success\":false}";
        }catch (AuthenticationException e) {
            result = "{\"message\":\"身份验证失败，用户名或密码错误！\",\"success\":false}";
        }
        stringView=new StringView(result);
        return new ModelAndView(stringView);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public @ResponseBody ModelAndView logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/slient/views/index");
    }
}
//public class LoginController extends AbstractCommandController{
//
//    public LoginController(){
//        setCommandClass(LoginForm.class);
//    }
//    @Override
//    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
//                                  Object command, BindException errors) throws Exception {
//
//        LoginForm loginForm=(LoginForm)command;
//        if(StringUtils.isEmpty(loginForm.getLoginName()) ||
//                StringUtils.isEmpty(loginForm.getPassword())){
//            return new ModelAndView("redirect:/index.html");
//        }
//        StringView stringView;
//        String result;
//        Subject subject= SecurityUtils.getSubject();
//        String sessionCaptcha=String.valueOf(subject.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
//        if(!loginForm.getCaptcha().equals(sessionCaptcha)){
//            result="{\"message\":\"验证码错误！\",\"success\":false}";
//            stringView=new StringView(result);
//            return new ModelAndView(stringView);
//        }
////        loginForm.setPassword(MD5Util.getMD5Format(loginForm.getPassword()));
//        UsernamePasswordToken token=new UsernamePasswordToken(loginForm.getLoginName(),loginForm.getPassword());
//        token.setRememberMe(true);
//
//        logger.info("token.getCredentials:"+token.getCredentials());
//        logger.info("token.getPassword:"+token.getPassword());
//
//        try {
//
//            //进行验证
//            subject.login(token);
//            subject.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
//            result="{\"message\":\"登录成功\",\"success\":true}";
//        }  catch (LockedAccountException e) {
//            result = "{\"message\":\"账户被锁定，请联系管理员！\",\"success\":false}";
//        } catch (AuthenticationException e) {
//            logger.info(e.getMessage()+"\n"+e.getCause());
//            result = "{\"message\":\"身份验证失败！\",\"success\":false}";
//        }
//
//        stringView=new StringView(result);
//        return new ModelAndView(stringView);
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
