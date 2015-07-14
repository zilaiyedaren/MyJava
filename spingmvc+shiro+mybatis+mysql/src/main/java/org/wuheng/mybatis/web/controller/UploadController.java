package org.wuheng.mybatis.web.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.wuheng.mybatis.web.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午8:18
 * To change this template use File | Settings | File Templates.
 */
@Controller("uploadController")
@RequestMapping("/upload")
public class UploadController{
    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public @ResponseBody String image(HttpServletRequest request, HttpServletResponse response){
        String stringView="";
        try {
            stringView=getFilePath(request,"picture");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return stringView;
     }
    @RequestMapping(value = "/text",method = RequestMethod.POST)
    public @ResponseBody String text(HttpServletRequest request, HttpServletResponse response){
        String stringView="";
        try {
            stringView=getFilePath(request,"text");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return stringView;
    }

    /**
     *上传图片和文本
     * @param request
     * @param subPath
     * @return
     * @throws Exception
     */
    private String getFilePath(HttpServletRequest request,String subPath) throws Exception{
        MultipartRequest multipartRequest=(MultipartRequest) request;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMM");
        String ymdText=simpleDateFormat.format(new Date());
        String ctxPath="/upload/"+ymdText+subPath; //文件夹名
        String systemPath=request.getSession().getServletContext().getRealPath(ctxPath);

        File targetDir=new File(systemPath);
        if(!targetDir.exists()){
            targetDir.mkdirs();
        }
        MultipartFile sourceFile=multipartRequest.getFile("file");
        String fileName=sourceFile.getOriginalFilename();
        String uuid=UUID.randomUUID().toString().replace("\\-", "");//返回一个随机UUID
        String suffix=FilenameUtils.getExtension(fileName);
        String targetFileName=uuid+"."+(suffix!=null?suffix:"");// 构成新文件名。
        File targetFile=new File(systemPath+"/"+targetFileName);
        sourceFile.transferTo(targetFile);
        String targetPath=ctxPath+"/"+targetFileName;

        Map<String,Object>map=new HashMap<String, Object>();
        map.put("path",targetPath);
        map.put("size",targetFile.length());
        return JsonUtil.getJsonStr(map);
    }
}
//public class UploadController extends AbstractController {
//    @Override
//    protected ModelAndView handleRequestInternal(HttpServletRequest request,
//                                                 HttpServletResponse response) throws Exception {
//        MultipartRequest multipartRequest=(MultipartRequest)request;
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMM");
//        String ymdText=simpleDateFormat.format(new Date());
//
//        String ctxPath="/upload/"+ymdText+"/picture"; //文件夹名
//        String systemPath=request.getRealPath(ctxPath);
//
//        File targetDir =new File(systemPath);
//        if(!targetDir.exists()){
//            targetDir.mkdirs();
//        }
//        MultipartFile sourceFile=multipartRequest.getFile("file");
//        String fileName=sourceFile.getOriginalFilename();
//        String uuid= UUID.randomUUID().toString().replace("\\-","");// 返回一个随机UUID
//        String suffix= FilenameUtils.getExtension(fileName);
//        String targetFileName=uuid+"."+(suffix!=null?suffix:"");// 构成新文件名。
//        File targetFile=new File(systemPath+"/"+targetFileName);
//        sourceFile.transferTo(targetFile);
//        String targetPath=ctxPath+"/"+targetFileName;
//        Map<String,Object>map=new HashMap<String, Object>();
//        map.put("path",targetPath);
//        map.put("size",targetFile.length());
//
//        StringView stringView=new StringView(JsonUtil.getJsonStr(map));
//        return new ModelAndView(stringView);  //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
