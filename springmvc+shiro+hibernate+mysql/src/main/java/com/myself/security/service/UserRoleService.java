package com.myself.security.service;

import java.util.List;

import com.myself.security.entity.main.UserRole;

public interface UserRoleService extends BaseService<UserRole, Long> {
	
	/**
	 * 根据userId，找到已分配的角色。
	 * 描述
	 * @param userId
	 * @return
	 */
	List<UserRole> find(Long userId);
}
