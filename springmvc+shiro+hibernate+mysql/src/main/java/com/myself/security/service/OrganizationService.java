
package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.Organization;
import com.myself.util.dwz.Page;

public interface OrganizationService extends BaseService<Organization, Long>{
	
	List<Organization> find(Long parentId, Page page);
	
	List<Organization> find(Long parentId, String name, Page page);
	
	Organization getTree();
}
