package org.wuheng.mybatis.web.controller.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.FilmForm;
import org.wuheng.mybatis.web.service.entertainment.FilmService;
import org.wuheng.mybatis.web.slient.pojo.Film;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

@Controller("filmController")
@RequestMapping("/slient/views/entertainment/film")
public class FilmController{
    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView filmList(){
        ModelAndView modelAndView=new ModelAndView("/slient/views/entertainment/film/list");
        return modelAndView;
    }
    //produces解决返回String字符串时，乱码问题，需要在每个@RequestMapping上面添加
    @RequestMapping(value = "/listFilm",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String filmList(FilmForm filmForm){
        DataGridResult dataGridResult=filmService.listFilm(filmForm);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }

    @RequestMapping(value = "/createFilm",method = RequestMethod.POST)
    public @ResponseBody String createFilm(Film film){
        filmService.createFilm(film);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
    @RequestMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/slient/views/entertainment/film/create");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody String delete(@RequestParam String filmIds){
        String[] idsStr=filmIds.split(",");
        Long[] filmsId=new Long[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            filmsId[i]=Long.valueOf(idsStr[i]);
        }

        filmService.deleteFilm(filmsId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
    @RequestMapping("/edit")
    public ModelAndView edit(){
        return new ModelAndView("/slient/views/entertainment/film/edit");
    }

    @RequestMapping(value = "/editFilm",method = RequestMethod.POST)
    public @ResponseBody String editFilm(Film film){
        System.out.println(film.toString());
        filmService.updateFilm(film);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
}
//public class FilmController extends MultiActionController {
//
//    @Autowired
//    private FilmService filmService;
//	// 影片列表
//	public ModelAndView filmList(HttpServletRequest request,
//			HttpServletResponse response, FilmForm formBean)
//			throws Exception {
////		FilmService filmService = ServiceFactory
////				.getBean(FilmService.class);
//        if(filmService==null){
//            System.out.println("filmService is null");
//        }
//		DataGridResult dg = filmService.listFilm(formBean);
//		StringView sv = new StringView(JsonUtil.getJsonStr(dg));
//		return new ModelAndView(sv);
//	}
//
//	// 创建影片
//	public ModelAndView createFilm(HttpServletRequest request,
//			HttpServletResponse response, Film film)throws Exception{
////		FilmService filmService = ServiceFactory
////				.getBean(FilmService.class);
//		filmService.createFilm(film);
//		StringView sv = new StringView(JsonUtil.getJsonStr(AjaxResult.SUCCESS));
//		return new ModelAndView(sv);
//	}
//}
