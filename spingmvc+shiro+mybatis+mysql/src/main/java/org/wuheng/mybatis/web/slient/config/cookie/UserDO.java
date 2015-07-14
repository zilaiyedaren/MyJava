package org.wuheng.mybatis.web.slient.config.cookie;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
public class UserDO {
    private Long id;

    private String userName;

    private String password;

    private long groupId;

    private String groupName;

    private Integer power;

    private Map<String, String> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Map<String, String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Map<String, String> authorities) {
        this.authorities = authorities;
    }
}
