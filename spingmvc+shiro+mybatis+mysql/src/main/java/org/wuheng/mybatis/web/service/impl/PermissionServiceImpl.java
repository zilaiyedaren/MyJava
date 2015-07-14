package org.wuheng.mybatis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.PermissionDao;
import org.wuheng.mybatis.web.formBean.PermissionForm;
import org.wuheng.mybatis.web.service.PermissionService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.Permission;
import org.wuheng.mybatis.web.slient.pojo.status.RolePermissionStatus;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission createPermission(Permission permission) {
        permissionDao.createPermission(permission);
        return permission;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deletePermission(Long... permissionIds) {
            permissionDao.deletePermission(permissionIds);
    }

    @Override
    public DataGridResult listPermission(PermissionForm permissionForm) {
        permissionForm.initOffset();
        int permissionCount=permissionDao.getPermissionCount(permissionForm);
        List<Permission> list=permissionDao.getPermissionList(permissionForm);
        return new DataGridResult(permissionCount,list);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
        CacheUtil.clearAuthInfo();
    }

    @Override
    public DataGridResult getAllPermissions(Long roleId) {
        List<RolePermissionStatus> list=permissionDao.getAllPermissions(roleId);

        return new DataGridResult(list);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
