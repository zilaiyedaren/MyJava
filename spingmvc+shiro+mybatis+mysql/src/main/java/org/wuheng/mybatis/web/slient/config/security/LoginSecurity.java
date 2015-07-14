package org.wuheng.mybatis.web.slient.config.security;

import org.apache.shiro.web.servlet.Cookie;
import org.springframework.web.servlet.ModelAndView;
import org.summercool.web.servlet.pipeline.PreProcessPipeline;
import org.wuheng.mybatis.web.slient.config.cookie.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
public class LoginSecurity extends AbstractSecurity implements PreProcessPipeline {

    private int order;

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean isPermitted(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if(match(request)){
             return true;
        }else{
            if(CookieUtils.isLogin(request)){
                if(CookieUtils.getUserPower(request)==1){
                    return true;
                }else if(CookieUtils.getUserPower(request)==2){
                    if(getUrlPathHelper().getLookupPathForRequest(request).startsWith("/dev")){
                        return true;
                    }else {
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    @Override
    public ModelAndView handleProcessInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new ModelAndView("redirect:/"+"index.html");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getOrder() {
        return order;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
