package org.wuheng.mybatis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.TagDao;
import org.wuheng.mybatis.web.formBean.TagForm;
import org.wuheng.mybatis.web.service.TagService;
import org.wuheng.mybatis.web.slient.pojo.Tag;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Override
    public DataGridResult getTagList(TagForm tagForm) {
        tagForm.initOffset();
        int count=tagDao.getTagCount(tagForm);
        List<Tag> list=tagDao.getTagList(tagForm);
        return new DataGridResult(count,list);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
