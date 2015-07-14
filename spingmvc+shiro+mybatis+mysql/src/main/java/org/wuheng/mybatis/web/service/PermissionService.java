package org.wuheng.mybatis.web.service;

import org.wuheng.mybatis.web.formBean.PermissionForm;
import org.wuheng.mybatis.web.slient.pojo.Permission;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long...permissionIds);
//    权限列表
    public DataGridResult listPermission(PermissionForm permissionForm);

    public void updatePermission(Permission permission);
//    获取所有权限 用户的权限状态
    public DataGridResult getAllPermissions(Long roleId);
}
