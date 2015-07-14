package org.myself.web.spring.controller;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class BaseController {
    public HttpServletRequest getRequest(){
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().setAttribute("a", "aa");
        System.out.println(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute("a"));
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
