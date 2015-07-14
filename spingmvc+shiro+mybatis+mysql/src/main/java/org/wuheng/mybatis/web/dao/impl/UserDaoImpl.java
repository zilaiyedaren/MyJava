package org.wuheng.mybatis.web.dao.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.UserDao;
import org.wuheng.mybatis.web.formBean.UserForm;
import org.wuheng.mybatis.web.slient.pojo.User;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:52
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    @Override
    public boolean createUser(User user) {
        return getSqlSession().insert("SHIRO-USER.createUser",user)>0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUser(User user) {
        getSqlSession().update("SHIRO-USER.updateUser",user);
    }

    @Override
    public void deleteUser(Long... userIds) {
        for(int i=0;i<userIds.length;i++){
            getSqlSession().delete("SHIRO-USER.deleteUser",userIds[i]);
        }
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("userId",userId);
        for(Long roleId:roleIds){
            map.put("roleId",roleId);
            getSqlSession().insert("SHIRO-USER.correlationRoles",map);
        }
    }

    @Override
    public User findByUsername(String username) {
        return (User) getSqlSession().selectOne("SHIRO-USER.findByUsername",username);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<String> findRoles(String username) {
        return new HashSet<String>(getSqlSession().selectList("SHIRO-USER.findRoles",username));  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<String> findPermissions(String username) {
        return new HashSet<String>(getSqlSession().selectList("SHIRO-USER.findPermissions",username));  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getUserCount(UserForm formBean) {
        return (Integer) getSqlSession().selectOne("SHIRO-USER.getUserCount",formBean);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserList(UserForm formBean) {
        return getSqlSession().selectList("SHIRO-USER.getUserList",formBean);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteUserRole(Long userId) {
        getSqlSession().delete("SHIRO-USER.deleteUserRole",userId);
    }
}
