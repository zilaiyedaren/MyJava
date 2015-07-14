package org.wuheng.mybatis.web.dao.entertainment;

import org.wuheng.mybatis.web.formBean.MusicForm;
import org.wuheng.mybatis.web.slient.pojo.Music;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */
public interface MusicDao {
    public int getMusicCount(MusicForm musicForm);

    public List<Music> getMusicList(MusicForm musicForm);

    public void createMusic(Music music);

    public void updateMusic(Music music);

    public void deleteMusic(Long... musicIds);
}
