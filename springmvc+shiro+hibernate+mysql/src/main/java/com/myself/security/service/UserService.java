/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, myself.com
 * Filename:		com.ygsoft.security.service.UserService.java
 * Class:			UserService
 * Date:			2012-8-7
 * Author:			<a href="mailto:myself@gmail.com">myself</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.User;
import com.myself.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:myself@gmail.com">myself</a>
 * Version  1.1.0
 * @since   2012-8-7 下午3:03:59 
 */

public interface UserService extends BaseService<User, Long>{
	
	User get(String username);
	
	List<User> find(Page page, String name);
}
