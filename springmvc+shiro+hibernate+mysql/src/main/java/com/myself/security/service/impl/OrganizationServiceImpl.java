
package com.myself.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.security.dao.OrganizationDao;
import com.myself.security.entity.main.Organization;
import com.myself.security.exception.ServiceException;
import com.myself.security.service.OrganizationService;
import com.myself.util.dwz.Page;
import com.myself.util.dwz.springdata.PageUtils;

@Service
@Transactional(readOnly=true)
public class OrganizationServiceImpl extends BaseServiceImpl<Organization, Long>implements OrganizationService {
	
	private OrganizationDao organizationDao;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public OrganizationServiceImpl(OrganizationDao organizationDao) {
		super((JpaRepository<Organization, Long>) organizationDao);
		this.organizationDao = organizationDao;
	}

	/**   
	 * @param id
	 * @throws ServiceException  
	 * @see com.myself.security.service.OrganizationService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) throws ServiceException {
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根组织。");
		}
		
		Organization organization = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(organization.getChildren().size() > 0){
			throw new ServiceException(organization.getName() + "组织下存在子组织，不允许删除。");
		}
		
		organizationDao.delete(id);
	}

	/**   
	 * @param parentId
	 * @param page
	 * @return  
	 * @see com.myself.security.service.OrganizationService#find(java.lang.Long, com.myself.util.dwz.Page)
	 */
	public List<Organization> find(Long parentId, Page page) {
		org.springframework.data.domain.Page<Organization> p = 
				organizationDao.findByParentId(parentId, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, p);
		return p.getContent();
	}

	/**   
	 * @param parentId
	 * @param name
	 * @param page
	 * @return  
	 * @see com.myself.security.service.OrganizationService#find(java.lang.Long, java.lang.String, com.myself.util.dwz.Page)
	 */
	public List<Organization> find(Long parentId, String name, Page page) {
		org.springframework.data.domain.Page<Organization> p = 
				organizationDao.findByParentIdAndNameContaining(parentId, name, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, p);
		return p.getContent();
	}
	
	/**
	 * 判断是否是根组织.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

	/**
	 * 
	 * @return  
	 * @see com.myself.security.service.OrganizationService#getTree()
	 */
	public Organization getTree() {
		List<Organization> list = organizationDao.findAllWithCache();
		
		List<Organization> rootList = makeTree(list);
				
		return rootList.get(0);
	}

	private List<Organization> makeTree(List<Organization> list) {
		List<Organization> parent = new ArrayList<Organization>();
		// get parentId = null;
		for (Organization e : list) {
			if (e.getParent() == null) {
				e.setChildren(new ArrayList<Organization>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	private void makeChildren(List<Organization> parent, List<Organization> children) {
		if (children.isEmpty()) {
			return ;
		}
		
		List<Organization> tmp = new ArrayList<Organization>();
		for (Organization c1 : parent) {
			for (Organization c2 : children) {
				c2.setChildren(new ArrayList<Organization>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}
}
