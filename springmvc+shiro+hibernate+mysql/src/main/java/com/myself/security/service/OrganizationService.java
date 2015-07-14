/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, myself.com
 * Filename:		com.ygsoft.security.service.OrganizationService.java
 * Class:			OrganizationService
 * Date:			2012-8-27
 * Author:			<a href="mailto:myself@gmail.com">myself</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.Organization;
import com.myself.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:myself@gmail.com">myself</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:56:25 
 */

public interface OrganizationService extends BaseService<Organization, Long>{
	
	List<Organization> find(Long parentId, Page page);
	
	List<Organization> find(Long parentId, String name, Page page);
	
	Organization getTree();
}
