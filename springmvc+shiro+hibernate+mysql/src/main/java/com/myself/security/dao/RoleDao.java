
package com.myself.security.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myself.security.entity.main.Role;
import org.springframework.stereotype.Repository;

public interface RoleDao extends JpaRepository<Role, Long> {
	Page<Role> findByNameContaining(String name, Pageable pageable);
}
