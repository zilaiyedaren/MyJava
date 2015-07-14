/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, myself.com
 * Filename:		com.ygsoft.security.service.impl.RoleServiceImpl.java
 * Class:			RoleServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:myself@gmail.com">myself</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.myself.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.security.dao.RoleDao;
import com.myself.security.entity.main.Role;
import com.myself.security.service.RoleService;
import com.myself.security.shiro.ShiroDbRealm;
import com.myself.util.dwz.Page;
import com.myself.util.dwz.springdata.PageUtils;

@Service
@Transactional(readOnly=true)
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

	private RoleDao roleDao;
	
	@Autowired(required = false)
	private ShiroDbRealm shiroRealm;

    public RoleServiceImpl(JpaRepository<Role, Long> jpaRepository) {
        super(jpaRepository);
    }

    /**
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		super((JpaRepository<Role, Long>) roleDao);
		this.roleDao = roleDao;
	}

	/**   
	 * @param role  
	 * @see com.myself.security.service.RoleService#update(com.myself.security.entity.main.Role)
	 */
	@Transactional
	public void update(Role role) {
		roleDao.save(role);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param id  
	 * @see com.myself.security.service.RoleService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		roleDao.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.myself.security.service.RoleService#find(com.myself.util.dwz.Page, java.lang.String)
	 */
	public List<Role> find(Page page, String name) {
		org.springframework.data.domain.Page<Role> roles = 
				(org.springframework.data.domain.Page<Role>)roleDao.findByNameContaining(name, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, roles);
		return roles.getContent();
	}

}
