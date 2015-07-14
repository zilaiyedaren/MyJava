package org.myself.web.spring.daoimpl;

import org.myself.web.spring.dao.BaseDao;
import org.myself.web.spring.utils.MyHibernateDaoSupport;
import org.myself.web.spring.utils.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("baseDao")
public class BaseDaoImpl extends MyHibernateDaoSupport implements BaseDao {

	public <T> void doDelete(T t) throws Exception {
		super.getHibernateTemplate().delete(t);
	}

	public <T> void doDelete(Class<T> entityClass, Integer id) throws Exception {
		super.getHibernateTemplate().delete(super.getHibernateTemplate().get(entityClass, id));
	}

	public <T> T findById(Class<T> entityClass, Integer id) throws Exception {
		return (T) super.getHibernateTemplate().get(entityClass, id);
	}

	public <T> void doCreate(T t) throws Exception {
		super.getHibernateTemplate().save(t); 
	}

	public <T> void doUpdate(T t) throws Exception {
		super.getHibernateTemplate().update(t);
	}

	public <T> List<T> findAll(String hql) throws Exception {
		return (List<T>)super.getHibernateTemplate().find(hql);
	}

	public <T> PageView<T> findPageView(final String hql, final Object[] objects, final int pageNo, final int pageSize) throws Exception {
		return (PageView<T>)super.findByPage(hql, objects, pageNo, pageSize);
	}

	public <T> List<T> findAll(String hql, Object[] params) throws Exception {
		return (List<T>) super.getHibernateTemplate().find(hql, params);
	}
}