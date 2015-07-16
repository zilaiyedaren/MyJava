package com.myself.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.security.dao.UserRoleDao;
import com.myself.security.entity.main.UserRole;
import com.myself.security.service.UserRoleService;

@Service
@Transactional(readOnly=true)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, Long> implements UserRoleService {

	private UserRoleDao userRoleDao;
	
	/**  
	 * 构造函数
	 * @param userRoleDao
	 */ 
	@Autowired
	public UserRoleServiceImpl(UserRoleDao userRoleDao) {
		super((JpaRepository<UserRole, Long>) userRoleDao);
		this.userRoleDao = userRoleDao;
	}

	/**   
	 * @param userId
	 * @return  
	 * @see com.myself.security.service.UserRoleService#find(Long)
	 */
	public List<UserRole> find(Long userId) {
		return userRoleDao.findByUserId(userId);
	}

}
