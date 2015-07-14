package org.wuheng.mybatis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.UserDao;

import org.wuheng.mybatis.web.formBean.UserForm;
import org.wuheng.mybatis.web.service.UserService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.User;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.PasswordHelper;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:11
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public User createUser(User user) {
        userDao.createUser(user);
        return user;
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.deleteUserRole(userId);
        userDao.correlationRoles(userId, roleIds);
        CacheUtil.clearAuthInfo();
    }

    @Override
    public User findByUsername(String username) {
        User user=userDao.findByUsername(username);
        return user;
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }

    @Override
    public DataGridResult listUser(UserForm formBean) {
        formBean.initOffset();
        int userCount = userDao.getUserCount(formBean);
        List<User> list = userDao.getUserList(formBean);
        return new DataGridResult(userCount, list);
    }

    @Override
    public void updateUser(User user) {
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
        CacheUtil.clearAuthInfo();
    }

    @Override
    public void deleteUser(Long... userIds) {
        for (int i = 0; i < userIds.length; i++) {
            userDao.deleteUserRole(userIds[i]);
        }
        userDao.deleteUser(userIds);
        CacheUtil.clearAuthInfo();
    }

}
