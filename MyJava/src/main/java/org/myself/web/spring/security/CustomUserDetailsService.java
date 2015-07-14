package org.myself.web.spring.security;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午8:55
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.myself.web.spring.serviceimpl.UsersServiceImpl;
import org.myself.web.spring.vo.TUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 一个自定义的类用来和数据库进行操作. 即以后要通过数据库保存权限.则需要实现UserDetailsService
 *自定义类，处理用户登录，和验证用户角色权限
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UsersServiceImpl usersService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails user = null;
        try {
            // 搜索数据库以匹配用户登录名.
            TUsers tusers =usersService.findByUsername(username);

            System.out.println( "数据库取出的用户为："+tusers.toString());

            // 用户名、密码、是否启用、是否被锁定、是否过期、权限
            try {
                user = new User(tusers.getUsername(), tusers.getPassword().toLowerCase(), true, true, true, true, getAuthorities(Integer.parseInt(tusers.getRole())));
            } catch (NumberFormatException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


        } catch (Exception e) {
            throw new UsernameNotFoundException("异常处理：检索用户信息未通过！");
        }

        return user;
    }

    /**
     * 获得访问角色权限列表
     * @return
     */
    public Collection<GrantedAuthority> getAuthorities(Integer role) {
//        System.out.println("取得的权限是 :" + role);
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        // 所有的用户默认拥有ROLE_USER权限
        if (role == 0) {
            System.out.println("普通用户");
            logger.debug("取得普通用户权限-->");
            authList.add(new SimpleGrantedAuthority("ROLE_USERS"));
        }
        // 如果参数role为1.则拥有ROLE_ADMIN权限
        if (role == 1) {
            logger.debug("取得ADMIN用户权限-->");
//            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
//        System.out.println(authList.size()+"  权限列表长度");
        return authList;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode();    //To change body of overridden methods use File | Settings | File Templates.
    }
}

