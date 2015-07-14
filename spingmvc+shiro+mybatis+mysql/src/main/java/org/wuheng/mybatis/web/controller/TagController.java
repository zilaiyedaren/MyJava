package org.wuheng.mybatis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wuheng.mybatis.web.formBean.TagForm;
import org.wuheng.mybatis.web.service.TagService;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;


@Controller("tagController")
@RequestMapping("/slient/views")
public class TagController{

    @Autowired
    private TagService tagService;

    @RequestMapping("/tagList")
    public @ResponseBody String tagList(TagForm tagForm){
        DataGridResult dataGridResult=tagService.getTagList(tagForm);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }
}
//public class TagController extends MultiActionController {
//
//	// 获取tag列表
//	public ModelAndView tagList(HttpServletRequest request,
//			HttpServletResponse response, TagForm formBean)
//			throws Exception {
//		TagService tagService = ServiceFactory.getBean(TagService.class);
//		DataGridResult dg = tagService.getTagList(formBean);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//}
