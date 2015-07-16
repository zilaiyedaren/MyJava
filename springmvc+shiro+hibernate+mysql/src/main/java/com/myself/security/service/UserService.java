package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.User;
import com.myself.util.dwz.Page;


public interface UserService extends BaseService<User, Long>{
	
	User get(String username);
	
	List<User> find(Page page, String name);
}
