package org.wuheng.mybatis.web.dao;

import org.wuheng.mybatis.web.formBean.PermissionForm;
import org.wuheng.mybatis.web.slient.pojo.Permission;
import org.wuheng.mybatis.web.slient.pojo.status.RolePermissionStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionDao {
    public boolean createPermission(Permission permission);

    public void deletePermission(Long...permissionIds);
//    获取权限数量
    public int getPermissionCount(PermissionForm permissionForm);
//    获取权限列表
    public List<Permission> getPermissionList(PermissionForm permissionForm);

    public void updatePermission(Permission permission);
//    获取所有权限
    public List<RolePermissionStatus> getAllPermissions(Long roleId);
}
