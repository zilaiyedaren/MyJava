/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, myself.com
 * Filename:		com.ygsoft.security.service.ModuleService.java
 * Class:			ModuleService
 * Date:			2012-8-6
 * Author:			<a href="mailto:myself@gmail.com">myself</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.Module;
import com.myself.util.dwz.Page;


/** 
 * 	
 * @author 	<a href="mailto:myself@gmail.com">myself</a>
 * Version  1.1.0
 * @since   2012-8-6 上午9:32:17 
 */

public interface ModuleService extends BaseService<Module, Long>{
	List<Module> find(Long parentId, Page page);
	
	List<Module> find(Long parentId, String name, Page page);
	
	Module getTree();
}
