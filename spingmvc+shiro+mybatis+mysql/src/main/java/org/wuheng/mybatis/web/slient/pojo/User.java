package org.wuheng.mybatis.web.slient.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-28
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable{
    private static final long serialVersionUID=1234782934729034483L;
    private Long id;
    private String username;
    private String password;
    private String salt="md5";
    private Boolean locked=Boolean.FALSE;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
    public String getCredentialsSalt() {
        return username + salt;
    }

    @Override
    public boolean equals(Object obj) {
       if(this==obj){
           return true;
       }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        User user=(User)obj;
        if(id != null ? !id.equals(user.id) : user.id != null){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\''
                + ", password='" + password + '\'' + ", salt='" + salt + '\''
                + ", locked=" + locked + "}";
    }
}
