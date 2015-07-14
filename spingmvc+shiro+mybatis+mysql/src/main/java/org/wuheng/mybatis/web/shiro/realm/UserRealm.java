package org.wuheng.mybatis.web.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wuheng.mybatis.web.service.ServiceFactory;
import org.wuheng.mybatis.web.service.UserService;
import org.wuheng.mybatis.web.service.impl.UserServiceImpl;
import org.wuheng.mybatis.web.slient.pojo.User;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-28
 * Time: 下午7:34
 * To change this template use File | Settings | File Templates.
 */

/**
 * shiro 对用户进行登录验证的类
 */
public class UserRealm extends AuthorizingRealm {
    protected Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    /**
     * 获取用户当前所拥有的权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        UserService userService= ServiceFactory.getBean(UserService.class);
        String userName=(String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(userName));
        authorizationInfo.setStringPermissions(userService.findPermissions(userName));

        return authorizationInfo;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
//        UserService userService=ServiceFactory.getBean(UserService.class);

        String userName=(String)token.getPrincipal();
        User user=userService.findByUsername(userName);

        if(user==null){
            logger.error("未找到账号");
            throw new UnknownAccountException();//未找到账号
        }
        if(Boolean.TRUE.equals(user.getLocked())){
            logger.warn("账号锁定");
            throw new LockedAccountException();//账号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配(可以自行实现)
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
                getName() // realm name
        );
        logger.info("getPrincipals:"+authenticationInfo.getPrincipals()+"\n");
        logger.info("getCredentials:"+authenticationInfo.getCredentials()+"\n");
        logger.info("getCredentialsSalt:"+authenticationInfo.getCredentialsSalt()+"\n");
        return authenticationInfo;
         //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void clearAllCacheAuthorizationInfo(){
        getAuthorizationCache().clear();
    }

    public void clearAllCacheAuthenticationInfo(){
        getAuthenticationCache().clear();
    }

    /**
     *用于清空整个缓存
     */
    public void clearAllCache(){
        clearAllCacheAuthenticationInfo();
        clearAllCacheAuthorizationInfo();
    }
}
