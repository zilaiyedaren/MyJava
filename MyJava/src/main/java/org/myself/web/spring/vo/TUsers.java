package org.myself.web.spring.vo;

import org.myself.web.spring.utils.MD5Util;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.sql.Timestamp;

/**
 * TUsers entity. Persistence Tools
 */

public class TUsers implements java.io.Serializable {
    private static final long serialVersionUID=1234782934729034483L;
	// Fields
	private Integer id;
	private String username;
	private String useremail;
	private String password;//加密后的密码
    private String expresspassword;//明文密码
	private String role;
    private Timestamp createtime;
	private Timestamp logintime;
    private Integer count=0;//登录次数
    private Boolean status=Boolean.TRUE; //状态

	public TUsers() {
	}

	public TUsers(String username, String useremail, String password, String role, Timestamp createtime,Timestamp logintime,String expresspassword) {
		this.username = username;
		this.useremail = useremail;
		this.password = password;
        this.expresspassword=expresspassword;
		this.role = role;
        this.createtime=createtime;
		this.logintime = logintime;
        this.count=0;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getExpresspassword() {
        return expresspassword;
    }

    public void setExpresspassword(String expresspassword) {
        this.expresspassword = expresspassword;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String status_text="启用状态";
        if(this.status==false){
            status_text="禁用状态";
        }
        return "TUsers{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                ", expresspassword='" + expresspassword + '\'' +
                ", role='" + role + '\'' +
                ", createtime=" + createtime +
                ", logintime=" + logintime +
                ", count=" + count +
                ", status=" + status_text +
                '}';
    }
}