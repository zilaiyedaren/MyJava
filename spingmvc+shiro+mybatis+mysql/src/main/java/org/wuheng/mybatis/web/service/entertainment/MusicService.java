package org.wuheng.mybatis.web.service.entertainment;

import org.wuheng.mybatis.web.formBean.ApkFrom;
import org.wuheng.mybatis.web.formBean.MusicForm;
import org.wuheng.mybatis.web.slient.pojo.Apk;
import org.wuheng.mybatis.web.slient.pojo.Music;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
public interface MusicService {
    public DataGridResult listMusic(MusicForm musicForm);
    public void createMusic(Music music);
    public void updateMusic(Music music);
    public void deleteMusic(Long... musicIds);
}
