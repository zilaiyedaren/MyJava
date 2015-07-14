package org.wuheng.mybatis.web.slient.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -34032346384057879L;
    private Long roleId;
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RolePermission that = (RolePermission) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId)
                : that.permissionId != null)
            return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result
                + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RolePermission{" + "roleId=" + roleId + ", permissionId="
                + permissionId + '}';
    }
}
