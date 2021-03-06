
package com.myself.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myself.security.entity.main.UserRole;
import org.springframework.stereotype.Repository;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserId(Long userId);
}
