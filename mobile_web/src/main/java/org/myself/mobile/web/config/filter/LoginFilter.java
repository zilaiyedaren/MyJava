package org.myself.mobile.web.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
* Created with IntelliJ IDEA.
* User: wuheng
* Date: 14-11-12
* Time: 上午11:40
* To change this template use File | Settings | File Templates.
*/
//过滤远程http URL请求，未登录，跳转到登录页面
public class LoginFilter implements Filter {
    private String gotoUrl = null;
    public void destroy() {
        // TODO Auto-generated method stub
        gotoUrl = null;
    }
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
//        System.out.println(req.getRequestURI());
        if(req.getRequestURI().indexOf(".do")>-1||req.getRequestURI().indexOf(".dhtml")>-1){
            if (session.getAttribute("user") == null) {
                res.sendRedirect(req.getContextPath() + gotoUrl);
                return ;
            }else{
                chain.doFilter(request,response);
            }
        }else{
            chain.doFilter(request,response);
        }
    }
    public void init(FilterConfig filterConfig) throws ServletException {
        gotoUrl = filterConfig.getInitParameter("url");
//        System.out.println("1");
    }
}

