package org.wuheng.mybatis.web.dao;

import org.wuheng.mybatis.web.formBean.RoleForm;
import org.wuheng.mybatis.web.slient.pojo.Role;
import org.wuheng.mybatis.web.slient.pojo.status.UserRoleStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDao {
    public boolean createRole(Role role);

    public void deleteUserRole(Long roleId);

    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId,Long...permissionIds);

    public int getRoleCount(RoleForm roleForm);
    public List<Role> getRoleList(RoleForm roleForm);
    public void updateRole(Role role);
//    获取所有角色
    public List<UserRoleStatus> getAllRoles(Long userId);
    public void deleteRolePermission(Long roleId);

}
