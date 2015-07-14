package org.wuheng.mybatis.web.slient.config.cookie;

import org.apache.commons.lang.StringUtils;
import org.summercool.web.module.cookie.CookieModule;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class CookieUtils {
    private static final String[] AUTHORITIES=new String[0];

    public static void writeCookie(HttpServletRequest request,UserDO userDO){
        if(request==null || userDO==null){
            throw new IllegalArgumentException();
        }

        CookieModule cookieModule=(CookieModule)request.getAttribute(CookieModule.COOKIE);
        if(cookieModule==null){
            throw new NullPointerException();
        }

        cookieModule.remove(CookieConstants.MANAGER_ID_COOKIE);
        cookieModule.remove(CookieConstants.MANAGER_PWD_COOKIE);
        cookieModule.remove(CookieConstants.MANAGER_NAME_COOKIE);
        cookieModule.remove(CookieConstants.MANAGER_POWER_COOKIE);

        cookieModule.set(CookieConstants.MANAGER_ID_COOKIE,userDO.getId().toString());

        try {
            cookieModule.set(CookieConstants.MANAGER_NAME_COOKIE, URLEncoder.encode(userDO.getUserName(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        cookieModule.set(CookieConstants.MANAGER_POWER_COOKIE,userDO.getPassword());
        cookieModule.set(CookieConstants.MANAGER_PWD_COOKIE,String.valueOf(userDO.getPower()));
    }

    public static void  clearCookie(HttpServletRequest request){
        CookieModule cookieModule=(CookieModule)request.getAttribute(CookieModule.COOKIE);
        if(cookieModule!=null){
            cookieModule.remove(CookieConstants.MANAGER_ID_COOKIE);
            cookieModule.remove(CookieConstants.MANAGER_NAME_COOKIE);
            cookieModule.remove(CookieConstants.MANAGER_PWD_COOKIE);
            cookieModule.remove(CookieConstants.MANAGER_POWER_COOKIE);
        }
    }
    public static Long getUserId(HttpServletRequest request){
        if(request==null){
            throw new IllegalArgumentException();
        }
        CookieModule cookieModule=(CookieModule)request.getAttribute(CookieConstants.MANAGER_ID_COOKIE);
       if(cookieModule==null){
           return null;
       }
        String idStr=cookieModule.get(CookieConstants.MANAGER_ID_COOKIE);
        if(StringUtils.isNumeric(idStr)){
            return Long.valueOf(idStr);
        }
        return null;
    }

    public static String getUserName(HttpServletRequest request){
        if (request == null) {
            throw new IllegalArgumentException();
        }
        CookieModule cookieModule = (CookieModule) request
                .getAttribute(CookieModule.COOKIE);

        if (cookieModule == null) {
            return null;
        }

        try {
            String name = cookieModule.get(CookieConstants.MANAGER_NAME_COOKIE);
            if (name == null) {
                return name;
            }
            return URLDecoder.decode(
                    cookieModule.get(CookieConstants.MANAGER_NAME_COOKIE), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    public static Integer getUserPower(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }
        CookieModule cookieModule = (CookieModule) request
                .getAttribute(CookieModule.COOKIE);

        if (cookieModule == null) {
            return null;
        }
        String idStr = cookieModule.get(CookieConstants.MANAGER_PWD_COOKIE);
        if (StringUtils.isNumeric(idStr)) {
            return Integer.valueOf(idStr);
        }
        return null;
    }

    public static String getUserPassword(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }
        CookieModule cookieModule = (CookieModule) request
                .getAttribute(CookieModule.COOKIE);

        if (cookieModule == null) {
            return null;
        }

        try {
            String password = cookieModule.get(CookieConstants.MANAGER_NAME_COOKIE);
            if (password == null) {
                return password;
            }
            return URLDecoder.decode(
                    cookieModule.get(CookieConstants.MANAGER_PWD_COOKIE), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    public static boolean isLogin(HttpServletRequest request) {
        Long userId = getUserId(request);
        String userName = getUserName(request);
        String userPassword = getUserPassword(request);
        Integer userPower = getUserPower(request);
        if (userId != null && userName != null && userPassword != null
                && userPower != null) {
            return true;
        }
        return false;
    }

    public static UserDO getCurrentUser(HttpServletRequest request){
        if(!isLogin(request)){
            return null;
        }
        Long userId=getUserId(request);
        String userName=getUserName(request);
        String password=getUserPassword(request);
        Integer userPower=getUserPower(request);

        UserDO userDO=new UserDO();
        userDO.setId(userId);
        userDO.setUserName(userName);
        userDO.setPassword(password);
        userDO.setPower(userPower);

        return userDO;
    }
}
