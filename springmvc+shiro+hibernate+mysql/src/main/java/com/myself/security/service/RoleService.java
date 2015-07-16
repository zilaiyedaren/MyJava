
package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.Role;
import com.myself.util.dwz.Page;


public interface RoleService extends BaseService<Role, Long>{
	
	List<Role> find(Page page, String name);
}
