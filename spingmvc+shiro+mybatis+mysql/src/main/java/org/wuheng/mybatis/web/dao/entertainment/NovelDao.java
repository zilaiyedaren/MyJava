package org.wuheng.mybatis.web.dao.entertainment;


import org.wuheng.mybatis.web.formBean.NovelForm;
import org.wuheng.mybatis.web.slient.pojo.Novel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public interface NovelDao {
    public int getNovelCount(NovelForm novelForm);

    public List<Novel> getNovelList(NovelForm novelForm);

    public void createNovel(Novel novel);

    public void deleteNovel(Long...novelId);

    public void updateNovel(Novel novel);
}
