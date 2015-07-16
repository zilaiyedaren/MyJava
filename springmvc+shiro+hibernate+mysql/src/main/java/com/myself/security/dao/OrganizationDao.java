
package com.myself.security.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.myself.security.entity.main.Organization;


public interface OrganizationDao extends JpaRepository<Organization, Long>{
	Page<Organization> findByParentId(Long parentId, Pageable pageable);
	
	Page<Organization> findByParentIdAndNameContaining(Long parentId, String name, Pageable pageable);
	
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="com.myself.security.entity.main")
		}
	)
	@Query("from Organization")
	List<Organization> findAllWithCache();
}
