package com.myself.security.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.security.dao.UserDao;
import com.myself.security.entity.main.User;
import com.myself.security.exception.ExistedException;
import com.myself.security.exception.ServiceException;
import com.myself.security.service.UserService;
import com.myself.security.shiro.ShiroDbRealm;
import com.myself.security.shiro.ShiroDbRealm.HashPassword;
import com.myself.util.dwz.Page;
import com.myself.util.dwz.springdata.PageUtils;


@Service
@Transactional(readOnly=true)
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	private ShiroDbRealm shiroRealm;
	
	/**  
	 * 构造函数
	 * @param userDao
	 */ 
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super((JpaRepository<User, Long>) userDao);
		this.userDao = userDao;
	}
	
	/**
	 * 
	 * @param user
	 * @throws ExistedException  
	 * @see com.myself.security.service.UserService# save(com.myself.security.entity.main.User)
	 */
	@Transactional
	public void save(User user) throws ExistedException {		
		if (userDao.findByUsername(user.getUsername()) != null) {
			throw new ExistedException("用户添加失败，登录名：" + user.getUsername() + "已存在。");
		}
		
		if (userDao.findByRealname(user.getRealname()) != null) {
			throw new ExistedException("用户添加失败，真实名：" + user.getRealname() + "已存在。");
		}
		
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
			HashPassword hashPassword = shiroRealm.encrypt(user.getPlainPassword());
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
		}
		
		userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**   
	 * @param user  
	 * @see com.myself.security.service.UserService# update(com.myself.security.entity.main.User)
	 */
	@Transactional
	public void update(User user) {
		//if (isSupervisor(user.getId())) {
		//	logger.warn("操作员{},尝试修改超级管理员用户", SecurityUtils.getSubject().getPrincipal());
		//	throw new ServiceException("不能修改超级管理员用户");
		//}
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		
		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
			HashPassword hashPassword = shiroRealm.encrypt(user.getPlainPassword());
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
		}
		
		userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**   
	 * @param id  
	 * @see com.myself.security.service.UserService# delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) throws ServiceException {
		if (isSupervisor(id)) {
			logger.warn("操作员{}，尝试删除超级管理员用户", SecurityUtils.getSubject()
					.getPrincipal() + "。");
			throw new ServiceException("不能删除超级管理员用户。");
		}
		userDao.delete(id);
	}

	/**   
	 * @param username
	 * @return  
	 * @see com.myself.security.service.UserService#get(java.lang.String)
	 */
	public User get(String username) {
		return userDao.findByUsername(username);
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.myself.security.service.UserService#find(com.myself.util.dwz.Page, java.lang.String)
	 */
	public List<User> find(Page page, String name) {
		org.springframework.data.domain.Page<User> pageUser = 
				userDao.findByUsernameContaining(name, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, pageUser);
		return pageUser.getContent();
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}
}
