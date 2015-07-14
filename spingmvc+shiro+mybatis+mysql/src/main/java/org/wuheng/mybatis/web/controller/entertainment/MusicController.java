package org.wuheng.mybatis.web.controller.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.MusicForm;
import org.wuheng.mybatis.web.service.entertainment.MusicService;
import org.wuheng.mybatis.web.slient.pojo.Music;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

@Controller("musicController")
@RequestMapping("/slient/views/entertainment/music")
public class MusicController{
    @Autowired
    private MusicService musicService;

    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("/slient/views/entertainment/music/list");
    }
    @RequestMapping("/musicList")
    public @ResponseBody String musicList(MusicForm musicForm){
        DataGridResult dataGridResult=musicService.listMusic(musicForm);

        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }
    @RequestMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/slient/views/entertainment/music/create");
    }
    @RequestMapping("/createMusic")
    public @ResponseBody String createMusic(Music music){
        musicService.createMusic(music);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody String delete(@RequestParam String musicIds){
        String[] idsStr=musicIds.split(",");
        Long[] musicsId=new Long[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            musicsId[i]=Long.valueOf(idsStr[i]);
        }
        musicService.deleteMusic(musicsId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(){
        return new ModelAndView("/slient/views/entertainment/music/edit");
    }
    @RequestMapping("/editMusic")
    public @ResponseBody String editMusic(Music music){
        System.out.println("---------------------"+music.toString()+"--------------------------");
        musicService.updateMusic(music);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
}
