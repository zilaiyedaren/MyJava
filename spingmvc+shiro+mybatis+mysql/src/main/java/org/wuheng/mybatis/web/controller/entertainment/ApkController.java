package org.wuheng.mybatis.web.controller.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wuheng.mybatis.web.formBean.ApkFrom;
import org.wuheng.mybatis.web.service.entertainment.ApkService;
import org.wuheng.mybatis.web.slient.pojo.Apk;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.DataGridResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

@Controller("apkController")
@RequestMapping("/slient/views/entertainment/apk")
public class ApkController{
    @Autowired
    private ApkService apkService;

    @RequestMapping("/list")
    public ModelAndView list(){
        return new ModelAndView("/slient/views/entertainment/apk/list");
    }
    @RequestMapping("/apkList")
    public @ResponseBody String apkList(ApkFrom apkFrom){
        DataGridResult dataGridResult=apkService.listApk(apkFrom);
        String stringView=JsonUtil.getJsonStr(dataGridResult);
        return stringView;
    }

    @RequestMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("/slient/views/entertainment/apk/create");
    }
    @RequestMapping(value = "/createApk",method = RequestMethod.POST)
    public @ResponseBody String createApk(@ModelAttribute Apk apk){
        System.out.println("---------------------------------------"+apk.toString());
        apkService.createApk(apk);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/delete")
    public @ResponseBody String delete(@RequestParam String apkIds){
        String[] idsStr=apkIds.split(",");
        Long[] apksId=new Long[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            apksId[i]=Long.valueOf(idsStr[i]);
        }
        apkService.deleteApk(apksId);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(){
        return new ModelAndView("/slient/views/entertainment/apk/edit");
    }
    @RequestMapping("/editApk")
    public @ResponseBody String editApk(Apk apk){
        apkService.updateApk(apk);
        String stringView=JsonUtil.getJsonStr(AjaxResult.SUCCESS);
        return stringView;
    }
}
