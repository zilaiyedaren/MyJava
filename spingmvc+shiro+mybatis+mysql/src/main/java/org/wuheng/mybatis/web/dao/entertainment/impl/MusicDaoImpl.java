package org.wuheng.mybatis.web.dao.entertainment.impl;

import org.springframework.stereotype.Repository;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.entertainment.MusicDao;
import org.wuheng.mybatis.web.formBean.MusicForm;
import org.wuheng.mybatis.web.slient.pojo.Music;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
@Repository("musicDao")
public class MusicDaoImpl extends SqlSessionDaoSupport implements MusicDao {
    @Override
    public int getMusicCount(MusicForm musicForm) {
        return (Integer)getSqlSession().selectOne("SHIRO-MUSIC.getMusicCount",musicForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Music> getMusicList(MusicForm musicForm) {
        return getSqlSession().selectList("SHIRO-MUSIC.getMusicList",musicForm);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createMusic(Music music) {
        getSqlSession().insert("SHIRO-MUSIC.createMusic",music);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateMusic(Music music) {
        getSqlSession().update("SHIRO-MUSIC.updateMusic",music);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteMusic(Long... musicIds) {
        for(int i=0;i<musicIds.length;i++){
            getSqlSession().delete("SHIRO-MUSIC.deleteMusic",musicIds[i]);
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
