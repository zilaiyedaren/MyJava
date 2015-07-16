
package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.Module;
import com.myself.util.dwz.Page;


public interface ModuleService extends BaseService<Module, Long>{
	List<Module> find(Long parentId, Page page);
	
	List<Module> find(Long parentId, String name, Page page);
	
	Module getTree();
}
