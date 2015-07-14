package org.myself.web.spring.dao;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
 */
import java.util.List;


public interface BaseDao {
    /**
     * 插入操作
     *
     * @param t
     *            保存的对象实体
     * @return void
     * @throws Exception
     */
    public <T> void doCreate(T t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     *            删除的对象实体
     * @return void
     * @throws Exception
     */
    public <T> void doDelete(T t) throws Exception;

    /**
     * 删除操作
     *
     *  删除的对象实体
     * @return void
     * @throws Exception
     */
    public <T> void doDelete(Class<T> entityClass, Integer id) throws Exception;

    /**
     * 更新操作
     *
     * @param t
     *            更新的对象实体
     * @return void
     * @throws Exception
     */
    public <T> void doUpdate(T t) throws Exception;

    /**
     * 根据主键ID进行实体查询
     *
     * @param entityClass
     *            传入的需返回的对象类型
     *
     * @param id
     *            主键ID
     * @return 查询的实体
     * @throws Exception
     */
    public <T> T findById(Class<T> entityClass, Integer id) throws Exception;

    /**
     * 根据传入的HQL语句、模糊查询关键字进行实体集合查询
     *
     * @param params
     *            传入的需查询的关键字
     *
     * @return 查询的实体集合
     * @throws Exception
     */
    public <T> List<T> findAll(String hql, Object[] params) throws Exception;

}
