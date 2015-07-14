package org.wuheng.mybatis.web.controller.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.NovelForm;
import org.wuheng.mybatis.web.service.entertainment.NovelService;
import org.wuheng.mybatis.web.slient.pojo.Novel;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

@Controller("novelController")
@RequestMapping("/slient/views/entertainment/novel")
public class NovelController{
   @Autowired
    private NovelService novelService;

    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("/slient/views/entertainment/novel/list");
    }
    @RequestMapping("/novelList")
    public @ResponseBody String novelList(NovelForm novelForm){
        DataGridResult dataGridResult=novelService.listNovel(novelForm);
        String stringView=stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }
    @RequestMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/slient/views/entertainment/novel/create");
    }

    @RequestMapping("/createNovel")
    public @ResponseBody String createNovel(Novel novel){
        novelService.createNovel(novel);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(){
        return new ModelAndView("/slient/views/entertainment/novel/edit");
    }
    @RequestMapping("/editNovel")
    public @ResponseBody String editNovel(Novel novel){
        novelService.updateNovel(novel);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody String deleteNovel(@RequestParam String novelIds){
        String[] idsStr=novelIds.split(",");
        Long[] novelsId=new Long[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            novelsId[i]=Long.valueOf(idsStr[i]);
        }
        novelService.deleteNovel(novelsId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }


}
//public class NovelController extends MultiActionController {
//
//    @Autowired
//	private NovelService novelService;
//
//    public ModelAndView novelList(HttpServletRequest request,
//			HttpServletResponse response, NovelForm formBean)
//			throws Exception {
////        NovelService novelService = ServiceFactory
////                .getBean(NovelService.class);
//		DataGridResult dg = novelService.listNovel(formBean);
//        StringView sv=null;
//        if(dg!=null){
//            sv= new StringView(JsonUtil.getJsonStr(dg));
//        }
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView createNovel(HttpServletRequest request,
//			HttpServletResponse response, Novel novel) throws Exception {
//        novelService.createNovel(novel);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//
//	public ModelAndView deleteNovel(HttpServletRequest request,
//			HttpServletResponse response) {
//		String[] strIds = request.getParameter("novelIds").split(",");
//		Long[] novelIds = new Long[strIds.length];
//		for (int i = 0; i < strIds.length; i++) {
//			novelIds[i] = Long.valueOf(strIds[i]);
//		}
//        novelService.deleteNovel(novelIds);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//	public ModelAndView updateNovel(HttpServletRequest request,
//			HttpServletResponse response,Novel novel){
//        novelService.updateNovel(novel);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//}
