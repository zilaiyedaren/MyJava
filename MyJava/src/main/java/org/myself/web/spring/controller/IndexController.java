package org.myself.web.spring.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.myself.web.spring.serviceimpl.UsersServiceImpl;
import org.myself.web.spring.utils.ImageView;
import org.myself.web.spring.vo.TUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-7-4
 * Time: 下午6:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "index.html")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "success.html")
    public String success(){
        return "success";
    }

    @RequestMapping(value ="anonymously.html")
    public String anonymously(){
        return "anonymously";
    }
    @RequestMapping(value = "register.html")
    public String register(){
        return "register";
    }

    /**
     * 注册后自动登录
     * @param user_name
     * @param user_email
     * @param user_psd
     * @param captchaText
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "createUser",method = RequestMethod.POST)
    public  String createUser(@RequestParam String user_name,@RequestParam String user_email,@RequestParam String user_psd,@RequestParam String captchaText,HttpServletRequest request)
                               throws Exception{
               System.out.println(user_name);
        if(captchaText.equalsIgnoreCase((String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            TUsers tUsers=usersService.findByUsername(user_name);
            if(tUsers==null){//这是一个新用户
                Timestamp timestamp=new Timestamp(new Date().getTime());//当前登录时时间
                String saltpassword=user_psd;
                user_psd=new Md5PasswordEncoder().encodePassword(user_psd,user_name);
                tUsers=new TUsers(user_name,user_email,user_psd,"1",timestamp,timestamp,saltpassword);

                usersService.doCreate(tUsers);
                /**
                 *  注册成功后自动登录
                 */
                //获得用户提交的用户名和密码信息
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user_name,user_psd);
                request.getSession();
                token.setDetails(new WebAuthenticationDetails(request));
                Authentication authenticatedUser = authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

                return "redirect:user/list.html";
            }
        }
        return "redirect:register.html";
    }

    @RequestMapping(value = "findpwd")
    public String findPassword(){
        return "findpwd";
    }

    @RequestMapping(value = "help")
    public String help(){
        return "help";
    }

    @RequestMapping(value = "captcha",method = RequestMethod.GET)
    public @ResponseBody ModelAndView captcha(HttpServletRequest request){
        //创建验证码文本
        String captchaText=captchaProducer.createText();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,captchaText);
        BufferedImage bufferedImage=captchaProducer.createImage(captchaText);
        ImageView imageView=new ImageView(bufferedImage);
        return new ModelAndView(imageView);
    }
}
