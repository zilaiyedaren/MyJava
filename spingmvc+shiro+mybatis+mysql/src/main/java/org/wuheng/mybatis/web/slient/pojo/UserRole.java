package org.wuheng.mybatis.web.slient.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID=-3159230824122167601L;

    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
       int result=userId !=null ? userId.hashCode() : 0;
        result = 31*result+(roleId != null?roleId.hashCode():0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
       if(this==obj){
           return true;
       }
        if(obj==null||getClass()!=obj.getClass()){
            return false;
        }
        UserRole userRole=(UserRole)obj;
        if(roleId!=null?!roleId.equals(userRole.roleId):userRole.roleId!=null){
              return false;
        }
        if(userId!=null?!userId.equals(userRole.userId):userRole.userId!=null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserRole{" + "userId=" + userId + ", roleId=" + roleId + '}';
    }
}
