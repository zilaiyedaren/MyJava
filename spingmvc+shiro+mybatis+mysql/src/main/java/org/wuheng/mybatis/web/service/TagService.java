package org.wuheng.mybatis.web.service;

import org.wuheng.mybatis.web.formBean.TagForm;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public interface TagService {
    // 获取tag列表信息
    DataGridResult getTagList(TagForm tagForm);

}
