package org.wuheng.mybatis.web.dao;

import org.wuheng.mybatis.web.formBean.UserForm;
import org.wuheng.mybatis.web.slient.pojo.User;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-28
 * Time: 下午6:40
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public boolean createUser(User user);

    public void  updateUser(User user);

    public void deleteUser(Long... userIds);

    public void correlationRoles(Long userId,Long... roleIds);

    public User findByUsername(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);

    //获取用户记录数
    public int getUserCount(UserForm formBean);

    //获取用户列表
    public List<User> getUserList(UserForm formBean);

    //删除角色
    public void deleteUserRole(Long userId);
}
