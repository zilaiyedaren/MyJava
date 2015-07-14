package org.wuheng.mybatis.web.service;

import org.wuheng.mybatis.web.formBean.UserForm;
import org.wuheng.mybatis.web.slient.pojo.User;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    public User createUser(User user);
    public void correlationRoles(Long userId,Long... roleIds);
    public User findByUsername(String username);
    public Set<String> findRoles(String username);
    public Set<String> findPermissions(String username);
//    获取用户列表dataGrid对象
    public DataGridResult listUser(UserForm form);
    public void updateUser(User user);
    public void deleteUser(Long...userId);

}
