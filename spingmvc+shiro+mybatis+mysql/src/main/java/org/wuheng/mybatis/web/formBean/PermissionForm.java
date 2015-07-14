package org.wuheng.mybatis.web.formBean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */
public class PermissionForm extends PagingAndSortingForm{
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
