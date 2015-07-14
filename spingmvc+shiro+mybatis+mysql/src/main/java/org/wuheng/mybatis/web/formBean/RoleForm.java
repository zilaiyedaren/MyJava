package org.wuheng.mybatis.web.formBean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:40
 * To change this template use File | Settings | File Templates.
 */
public class RoleForm extends PagingAndSortingForm{
    private String roleName;//角色名称

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
