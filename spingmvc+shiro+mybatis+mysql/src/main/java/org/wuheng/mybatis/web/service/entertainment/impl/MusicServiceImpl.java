package org.wuheng.mybatis.web.service.entertainment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.entertainment.MusicDao;
import org.wuheng.mybatis.web.formBean.MusicForm;
import org.wuheng.mybatis.web.service.entertainment.MusicService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.Music;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

    @Override
    public DataGridResult listMusic(MusicForm musicForm) {
        musicForm.initOffset();
        int musicCount=musicDao.getMusicCount(musicForm);
        List<Music> list=musicDao.getMusicList(musicForm);
        return new DataGridResult(musicCount,list);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createMusic(Music music) {
        musicDao.createMusic(music);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateMusic(Music music) {
        musicDao.updateMusic(music);
        CacheUtil.clearAuthInfo();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteMusic(Long... musicIds) {
        musicDao.deleteMusic(musicIds);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
