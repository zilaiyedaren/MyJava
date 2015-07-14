package org.wuheng.mybatis.web.service.entertainment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.entertainment.NovelDao;
import org.wuheng.mybatis.web.formBean.NovelForm;
import org.wuheng.mybatis.web.service.entertainment.NovelService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.Novel;
import org.wuheng.mybatis.web.utils.DataGridResult;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Service("novelService")
public class NovelServiceImpl implements NovelService {
    @Autowired
    private NovelDao novelDao;

    @Override
    public DataGridResult listNovel(NovelForm novelForm) {
        novelForm.initOffset();
        int novelCount=novelDao.getNovelCount(novelForm);
        List<Novel> list=novelDao.getNovelList(novelForm);
        return new DataGridResult(novelCount,list);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createNovel(Novel novel) {
//        novel.setCreated(new Date());
        novelDao.createNovel(novel);
    }

    @Override
    public void deleteNovel(Long... novelIds) {
        novelDao.deleteNovel(novelIds);
    }

    @Override
    public void updateNovel(Novel novel) {
        novelDao.updateNovel(novel);
        CacheUtil.clearAuthInfo();
    }
}
