
package com.myself.security.service;

import java.io.Serializable;
import java.util.List;

import com.myself.util.dwz.Page;

public interface BaseService<T, ID extends Serializable> {
	void save(T entity);
	
	void update(T entity);
	
	void delete(ID id);
	
	T get(ID id);
	
	List<T> findAll();
	
	List<T> findAll(Page page);
}
