package org.wuheng.mybatis.web.dao.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.PermissionDao;
import org.wuheng.mybatis.web.formBean.PermissionForm;
import org.wuheng.mybatis.web.slient.pojo.Permission;
import org.wuheng.mybatis.web.slient.pojo.status.RolePermissionStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */
@Repository("PermissionDao")
public class PermissionDaoImpl extends SqlSessionDaoSupport implements PermissionDao {
    @Override
    public boolean createPermission(Permission permission) {
        return getSqlSession().insert("SHIRO-PERMISSION.createPermission",permission)>0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deletePermission(Long... permissionIds) {
        for(int i=0;i<permissionIds.length;i++){
            getSqlSession().delete("SHIRO-PERMISSION.deletePermission",permissionIds[i]);
        }
    }

    @Override
    public int getPermissionCount(PermissionForm permissionForm) {
        return (Integer)getSqlSession().selectOne("SHIRO-PERMISSION.getPermissionCount",permissionForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getPermissionList(PermissionForm permissionForm) {
        return getSqlSession().selectList("SHIRO-PERMISSION.getPermissionList",permissionForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updatePermission(Permission permission) {
        getSqlSession().update("SHIRO-PERMISSION.updatePermission",permission);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RolePermissionStatus> getAllPermissions(Long roleId) {
        return getSqlSession().selectList("SHIRO-PERMISSION.getAllPermissions",roleId);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
