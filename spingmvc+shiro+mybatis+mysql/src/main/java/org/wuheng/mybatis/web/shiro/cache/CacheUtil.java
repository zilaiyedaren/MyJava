package org.wuheng.mybatis.web.shiro.cache;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.wuheng.mybatis.web.shiro.realm.UserRealm;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */

/**
 * 权限，角色，用户的认证信息发生变化 调用此方法清除认证信息
 */
public class CacheUtil {
    public static void clearAuthInfo(){
        RealmSecurityManager securityManager=(RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm userRealm=(UserRealm) securityManager.getRealms().iterator().next();
        userRealm.clearAllCache();
    }
}
