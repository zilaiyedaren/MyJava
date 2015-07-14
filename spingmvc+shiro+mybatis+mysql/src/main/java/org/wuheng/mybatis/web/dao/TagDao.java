package org.wuheng.mybatis.web.dao;

import org.wuheng.mybatis.web.formBean.TagForm;
import org.wuheng.mybatis.web.slient.pojo.Tag;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午1:12
 * To change this template use File | Settings | File Templates.
 */
public interface TagDao {
    public int getTagCount(TagForm tagForm);
    public List<Tag> getTagList(TagForm tagForm);
    public Tag getTagById(Long id);
}
