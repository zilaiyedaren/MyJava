/**
 * <pre>
 * Filename:		com.ygsoft.security.dao.UserRoleDao.java
 * Class:			UserRoleDao
 * </pre>
 **/
 
package com.myself.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myself.security.entity.main.UserRole;


public interface UserRoleDao extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserId(Long userId);
}
