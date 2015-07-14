/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, myself.com
 * Filename:		com.ygsoft.security.service.GenericService.java
 * Class:			GenericService
 * Date:			2012-10-30
 * Author:			<a href="mailto:myself@gmail.com">myself</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.myself.security.service;

import java.io.Serializable;
import java.util.List;

import com.myself.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:myself@gmail.com">myself</a>
 * Version  1.1.0
 * @since   2012-10-30 上午11:16:24 
 */

public interface BaseService<T, ID extends Serializable> {
	void save(T entity);
	
	void update(T entity);
	
	void delete(ID id);
	
	T get(ID id);
	
	List<T> findAll();
	
	List<T> findAll(Page page);
}
