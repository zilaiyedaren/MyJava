package org.wuheng.mybatis.web.formBean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午7:53
 * To change this template use File | Settings | File Templates.
 */
public class CorrelationPermissionForm {
    private Long roleId;
    private String permissionIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
