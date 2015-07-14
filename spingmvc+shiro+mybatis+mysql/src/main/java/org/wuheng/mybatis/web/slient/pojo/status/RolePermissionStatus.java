package org.wuheng.mybatis.web.slient.pojo.status;

import org.wuheng.mybatis.web.slient.pojo.Permission;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class RolePermissionStatus extends Permission {
    private static final long serialVersionUID = 7311976722852004766L;
    private Boolean checked = Boolean.FALSE;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
