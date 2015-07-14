package org.wuheng.mybatis.web.dao.entertainment.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.entertainment.NovelDao;
import org.wuheng.mybatis.web.formBean.NovelForm;
import org.wuheng.mybatis.web.slient.pojo.Novel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
@Repository("novelDao")
public class NovelDaoImpl extends SqlSessionDaoSupport implements NovelDao{
    @Override
    public int getNovelCount(NovelForm novelForm) {
        return (Integer) getSqlSession().selectOne("SHIRO-NOVEL.getNovelCount",novelForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Novel> getNovelList(NovelForm novelForm) {
        return getSqlSession().selectList("SHIRO-NOVEL.getNovelList",novelForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createNovel(Novel novel) {
        getSqlSession().insert("SHIRO-NOVEL.createNovel",novel);
    }

    @Override
    public void deleteNovel(Long... novelIds) {
        for(int i=0;i<novelIds.length;i++){
            getSqlSession().delete("SHIRO-NOVEL.deleteNovel",novelIds[i]);
        }
    }

    @Override
    public void updateNovel(Novel novel) {
        getSqlSession().update("SHIRO-NOVEL.updateNovel",novel);
    }
}
