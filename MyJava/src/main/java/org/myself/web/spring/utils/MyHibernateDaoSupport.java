package org.myself.web.spring.utils;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午9:26
 * To change this template use File | Settings | File Templates.
 */
public class MyHibernateDaoSupport extends HibernateDaoSupport {
    /**
     * 使用hql语句进行分页查询
     *
     * @param hql
     *            需要查询的hql语句
     * @param offset
     *            第一条记录索引
     * @param pageSize
     *            每页需要显示的记录数
     * @return 当前页的所有记录
     */
    public <T> PageView findByPage(final String hql, final int offset,final int pageSize)throws Exception {
        PageView<T> pv = new PageView<T>();
        int totalNo = Integer.parseInt(getTotal(hql, null).toString());
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                // 执行Hibernate分页查询
                Query query = session.createQuery(hql);
                List result = query.setFirstResult((offset - 1) * pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        pv.setCp(offset);
        pv.setLs(pageSize);
        pv.setPageList(list);
        pv.setTotalNo(totalNo);
        return pv;
    }

    /**
     * 使用hql语句进行分页查询
     *
     * @param hql
     *            需要查询的hql语句
     * @param value
     *            如果hql有一个参数需要传入，value就是传入hql语句的参数
     * @param offset
     *            第一条记录索引
     * @param pageSize
     *            每页需要显示的记录数
     * @return 当前页的所有记录
     */
    public<T>  PageView findByPage(final String hql, final Object value,final int offset, final int pageSize) throws Exception{
        PageView<T> pv = new PageView<T>();
        int totalNo = Integer.parseInt(getTotal(hql, new Object[]{value}).toString());
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                // 执行Hibernate分页查询
                Query query = session.createQuery(hql);
                // 为hql语句传入参数
                query.setParameter(0, value);
                List result = query.setFirstResult((offset - 1) * pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        pv.setCp(offset);
        pv.setLs(pageSize);
        pv.setPageList(list);
        pv.setTotalNo(totalNo);
        return pv;
    }

    /**
     * 使用hql语句进行分页查询
     *
     * @param hql
     *            需要查询的hql语句
     * @param values
     *            如果hql有多个个参数需要传入，values就是传入hql的参数数组
     * @param offset
     *            第一条记录索引
     * @param pageSize
     *            每页需要显示的记录数
     * @return 当前页的所有记录
     */
    public <T>  PageView findByPage(final String hql, final Object[] values,final int offset, final int pageSize)throws Exception {
        PageView<T> pv = new PageView<T>();
        int totalNo = Integer.parseInt(getTotal(hql, values).toString());
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                // 执行Hibernate分页查询
                Query query = session.createQuery(hql);
                // 为hql语句传入参数
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
                List result = query.setFirstResult((offset - 1) * pageSize).setMaxResults(pageSize).list();
                return result;
            }
        });
        pv.setCp(offset);
        pv.setLs(pageSize);
        pv.setPageList(list);
        pv.setTotalNo(totalNo);
        return pv;
    }
    // 取得所有记录数
    private Long getTotal(String xql, Object[] keys) throws Exception {
        String xxql = null;
        int index = xql.indexOf("from");
        if (index != -1) {
            xxql = "select count(*) " + xql.substring(index);
        }
        return (Long) getHibernateTemplate().find(xxql, keys).get(0);
    }
}
