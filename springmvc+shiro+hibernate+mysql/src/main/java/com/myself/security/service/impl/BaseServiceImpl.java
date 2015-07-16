package com.myself.security.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.myself.security.service.BaseService;
import com.myself.util.dwz.Page;
import com.myself.util.dwz.springdata.PageUtils;


public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	private JpaRepository<T, ID> jpaRepository;
	
	public BaseServiceImpl(JpaRepository<T, ID> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	/**   
	 * @param entity  
	 * @see com.myself.security.service.BaseService#save(java.lang.Object)
	 */
	@Transactional
	@Override
	public void save(T entity) {
		jpaRepository.save(entity);
	}

	/**   
	 * @param entity  
	 * @see com.myself.security.service.BaseService#update(java.lang.Object)
	 */
	@Transactional
	@Override
	public void update(T entity) {
		jpaRepository.save(entity);
	}

	/**   
	 * @param id  
	 * @see com.myself.security.service.BaseService#delete(java.io.Serializable)
	 */
	@Transactional
	@Override
	public void delete(ID id) {
		jpaRepository.delete(id);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.myself.security.service.BaseService#get(java.io.Serializable)
	 */
	@Override
	public T get(ID id) {
		return jpaRepository.findOne(id);
	}

	/**   
	 * @return  
	 * @see com.myself.security.service.BaseService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return jpaRepository.findAll();
	}

	/**   
	 * @param page
	 * @return  
	 * @see com.myself.security.service.BaseService#findAll(com.myself.util.dwz.Page)
	 */
	@Override
	public List<T> findAll(Page page) {
		org.springframework.data.domain.Page<T> springDataPage = jpaRepository.findAll(PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, springDataPage);
		return springDataPage.getContent();
	}
}
