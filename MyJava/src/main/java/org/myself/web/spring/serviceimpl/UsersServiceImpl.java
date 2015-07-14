package org.myself.web.spring.serviceimpl;

import java.util.List;

import org.myself.web.spring.dao.IUsersDao;
import org.myself.web.spring.utils.PageView;
import org.myself.web.spring.vo.TUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl  {

    @Autowired
	private IUsersDao userDao;

	public void doCreate(TUsers users) throws Exception {
		this.userDao.doCreate(users); 
	}

	public void doDelete(int id) throws Exception {
		this.userDao.doDelete(findById(id));

	}

	public void doUpdate(TUsers users) throws Exception {
		this.userDao.doUpdate(users);

	}

	public List<TUsers> findAll(String keyword) throws Exception {
		return this.userDao.findAll("From TUsers where username like ?", new Object[] { "%" + keyword + "%" });
	}

	public TUsers findById(int id) throws Exception {
		return this.userDao.findById(TUsers.class, id);
	}

	public TUsers findLogin(TUsers tusers) throws Exception {
		TUsers t = this.userDao.findLogin(tusers);
		if (t != null) {
			return t;
		} else {
			return null;
		}
	}

	public PageView<TUsers> findAll(String keyword, int offset, int pageSize) throws Exception {
		return this.userDao.findAll(keyword, offset, pageSize);
	}
	public TUsers findByUsername(String username) throws Exception {
		TUsers t = userDao.findByUsername(username);
		if (t != null) {
			return t;
		} else {
			return null;
		}
	}
}
