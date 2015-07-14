package org.wuheng.mybatis.web.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
@Controller("indexController")
@RequestMapping("/slient/views")
public class IndexController{
     @RequestMapping(value = "/index",method = RequestMethod.GET)
     public @ResponseBody ModelAndView index() throws Exception{
         Subject subject=SecurityUtils.getSubject();
         boolean flag=subject.isAuthenticated();
         ModelMap modelMap=new ModelMap();
         if(flag){
             modelMap.addAttribute("login","true");
         }else{
             modelMap.addAttribute("login","false");
         }
         return new ModelAndView("/slient/views/index",modelMap);
     }

    @RequestMapping(value = "/left",method = RequestMethod.GET)
    public @ResponseBody ModelAndView left(){
        return new ModelAndView("/slient/views/left");
    }
}
    //public class IndexController extends AbstractController{
//    @Override
//    public ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response)
//        throws Exception{
//        Subject subject= SecurityUtils.getSubject();
//        boolean flag=subject.isAuthenticated();
//        ModelMap mp=new ModelMap();
//        if(flag){
//            mp.addAttribute("login", "true");
//        } else{
//            mp.addAttribute("login","false");
//        }
//        return new ModelAndView("/slient/views/index",mp);
//    }
//}

