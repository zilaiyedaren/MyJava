package org.myself.web.spring.daoimpl;

import java.util.List;
import org.myself.web.spring.dao.IUsersDao;
import org.myself.web.spring.utils.PageView;
import org.myself.web.spring.vo.TUsers;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class IUsersDaoImpl extends BaseDaoImpl implements IUsersDao{

    /**
     * 登陆查询
     * @return 查询的实体
     * @throws Exception
     */
    public TUsers findLogin(TUsers tusers) throws Exception {
        String hql = "from TUsers where username = ? and password=?";
        Object[] objects = new Object[] { tusers.getUsername(), tusers.getPassword() };
        List<TUsers> list = (List<TUsers>)super.getHibernateTemplate().find(hql, objects);
        if (list.size() >= 1) {
            return list.get(0);
        }
        return null;
    }

    public <T> PageView<TUsers> findAll(String keyword, int offset, int pageSize) throws Exception {
        String hql = "from TUsers where username like ?";
        Object[] objects = new Object[] { "%" + keyword + "%" };
        return super.findPageView(hql, objects, offset, pageSize);
    }

    public TUsers findByUsername(String username) throws Exception {

        String hql = "from TUsers where username = ?";
        Object[] objects = new Object[] {username};
        List<TUsers> list = (List<TUsers>)super.getHibernateTemplate().find(hql, objects);
        System.out.println(list.toString());
        if (list.size() >= 1) {
            return list.get(0);
        }
        return null;
    }

}
