package org.wuheng.mybatis.web.service.entertainment;

import org.wuheng.mybatis.web.formBean.NovelForm;
import org.wuheng.mybatis.web.slient.pojo.Novel;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public interface NovelService {
    public DataGridResult listNovel(NovelForm novelForm);

    public void createNovel(Novel novel);

    public void deleteNovel(Long...novelIds);

    public void updateNovel(Novel novel);
}
