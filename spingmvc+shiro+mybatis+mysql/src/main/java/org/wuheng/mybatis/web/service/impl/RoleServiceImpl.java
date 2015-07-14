package org.wuheng.mybatis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.RoleDao;
import org.wuheng.mybatis.web.formBean.RoleForm;
import org.wuheng.mybatis.web.service.RoleService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.Role;
import org.wuheng.mybatis.web.slient.pojo.status.UserRoleStatus;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role createRole(Role role) {
        roleDao.createRole(role);
        return role;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteRole(Long... roleIds) {
        for(int i=0;i<roleIds.length;i++){
            roleDao.deleteUserRole(roleIds[i]);
            roleDao.deleteRole(roleIds[i]);
            roleDao.correlationPermissions(roleIds[i]);
        }
        CacheUtil.clearAuthInfo();
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.deleteRolePermission(roleId);
        roleDao.correlationPermissions(roleId,permissionIds);
        CacheUtil.clearAuthInfo();
    }

    @Override
    public DataGridResult listRole(RoleForm roleForm) {
        roleForm.initOffset();
        int userCount=roleDao.getRoleCount(roleForm);
        List<Role> list=roleDao.getRoleList(roleForm);
        return new DataGridResult(userCount,list);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);

    }

    @Override
    public DataGridResult getAllRoles(Long userId) {
        List<UserRoleStatus> list=roleDao.getAllRoles(userId);
        return new DataGridResult(list);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
