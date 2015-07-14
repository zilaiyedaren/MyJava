package org.myself.web.spring.dao;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午9:16
 * To change this template use File | Settings | File Templates.
 */
import org.myself.web.spring.utils.PageView;
import org.myself.web.spring.vo.TUsers;


public interface IUsersDao extends BaseDao {
    /**
     * 根据传入的对象实体进行登陆查询
     *
     * @param tusers
     *            登陆对象
     * @return 登陆验证结果
     * @throws Exception
     */
    public TUsers findLogin(TUsers tusers) throws Exception;

    /**
     * 根据关键字、条数、页数进行查询的实体集合
     *
     * @param keyword
     *            传入的需查询的关键字
     * @param offset
     *            条数
     * @param pageSize
     *            页数
     * @return 查询的实体集合
     * @throws Exception
     */
    public <T> PageView<TUsers> findAll(String keyword, int offset, int pageSize) throws Exception;

    /**
     * 根据传入的用户名进行实体查询
     *
     * @param username
     *            用户名
     * @return  查询的用户对象
     * @throws Exception
     */
    public TUsers findByUsername(String username) throws Exception;
}

