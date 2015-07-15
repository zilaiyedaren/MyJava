package org.myself.mobile.web.config.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-13
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class LoginInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception{
        // 取得请求相关的ActionContext实例
        ActionContext ctx = invocation.getInvocationContext();
        Map session = ctx.getSession();
        String user = (String) session.get("username");
        System.out.println("hello");
        // 如果没有登陆，或者登陆所有的用户名不是yuewei，都返回重新登陆
        if (user != null && user.equals("wh")) {

            return invocation.invoke();
        }
        ctx.put("tip", "你还没有登录");
        return Action.LOGIN;
    }
}
