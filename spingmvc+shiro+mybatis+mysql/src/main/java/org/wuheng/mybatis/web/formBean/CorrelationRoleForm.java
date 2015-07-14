package org.wuheng.mybatis.web.formBean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午7:52
 * To change this template use File | Settings | File Templates.
 */
public class CorrelationRoleForm {
    private Long userId;
    private String roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
