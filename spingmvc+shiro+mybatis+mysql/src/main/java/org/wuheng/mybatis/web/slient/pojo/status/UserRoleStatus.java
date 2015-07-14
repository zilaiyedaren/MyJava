package org.wuheng.mybatis.web.slient.pojo.status;

import org.wuheng.mybatis.web.slient.pojo.Role;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:57
 * To change this template use File | Settings | File Templates.
 */
public class UserRoleStatus extends Role {
    private static final long serialVersionUID = 3304993999271303682L;
    private Boolean checked = Boolean.FALSE;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
