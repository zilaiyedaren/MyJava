package org.wuheng.mybatis.web.service;

import org.wuheng.mybatis.web.formBean.RoleForm;
import org.wuheng.mybatis.web.slient.pojo.Role;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:37
 * To change this template use File | Settings | File Templates.
 */
public interface RoleService {
    public Role createRole(Role role);

    public void deleteRole(Long...roleIds);

    //添加角色-权限之间关系
    public void correlationPermissions(Long roleId,Long...permissionIds);
    public DataGridResult listRole(RoleForm roleForm);
    public void updateRole(Role role);
    public DataGridResult getAllRoles(Long userId);

}
