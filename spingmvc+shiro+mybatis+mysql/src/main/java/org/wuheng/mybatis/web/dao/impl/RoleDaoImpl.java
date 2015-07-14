package org.wuheng.mybatis.web.dao.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.RoleDao;
import org.wuheng.mybatis.web.formBean.RoleForm;
import org.wuheng.mybatis.web.slient.pojo.Role;
import org.wuheng.mybatis.web.slient.pojo.status.UserRoleStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
@Repository("roleDao")
public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleDao {
    @Override
    public boolean createRole(Role role) {
        return getSqlSession().insert("SHIRO-ROLE.createRole",role)>0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteUserRole(Long roleId) {
        getSqlSession().delete("SHIRO-ROLE.deleteUserRole",roleId);
    }

    @Override
    public void deleteRole(Long roleId) {
        getSqlSession().delete("SHIRO-ROLE.deleteRole",roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("roleId",roleId);
        for(Long permissionId:permissionIds){
            map.put("permissionId",permissionId);
            getSqlSession().insert("SHIRO-ROLE.correlationPermissions",map);
        }
    }

    @Override
    public int getRoleCount(RoleForm roleForm) {
        return (Integer) getSqlSession().selectOne("SHIRO-ROLE.getRoleCount",roleForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRoleList(RoleForm roleForm) {
        return getSqlSession().selectList("SHIRO-ROLE.getRoleList",roleForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateRole(Role role) {
        getSqlSession().update("SHIRO-ROLE.updateRole",role);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRoleStatus> getAllRoles(Long userId) {
        return getSqlSession().selectList("SHIRO-ROLE.getAllRoles",userId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteRolePermission(Long roleId) {
        getSqlSession().delete("SHIRO-ROLE.deleteRolePermission",roleId);
    }
}
