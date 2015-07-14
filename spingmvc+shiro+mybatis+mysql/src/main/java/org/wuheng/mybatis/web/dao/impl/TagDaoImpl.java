package org.wuheng.mybatis.web.dao.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.TagDao;
import org.wuheng.mybatis.web.formBean.TagForm;
import org.wuheng.mybatis.web.slient.pojo.Tag;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:04
 * To change this template use File | Settings | File Templates.
 */
@Repository("tagDao")
public class TagDaoImpl extends SqlSessionDaoSupport implements TagDao {
    @Override
    public int getTagCount(TagForm tagForm) {
        return (Integer)getSqlSession().selectOne("SHIRO-TAG.getTagCount",tagForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tag> getTagList(TagForm tagForm) {
        return getSqlSession().selectList("SHIRO-TAG.getTagList",tagForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Tag getTagById(Long id) {
        return (Tag)getSqlSession().selectOne("SHIRO-TAG.getTagById",id);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
