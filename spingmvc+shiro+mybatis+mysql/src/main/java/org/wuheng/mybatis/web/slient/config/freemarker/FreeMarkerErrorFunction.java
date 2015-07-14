package org.wuheng.mybatis.web.slient.config.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.support.BindStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午5:49
 * To change this template use File | Settings | File Templates.
 */
public class FreeMarkerErrorFunction implements TemplateMethodModelEx{
    @Override
    public Object exec(@SuppressWarnings("rawtypes")List list) throws TemplateModelException {
        //解包FreeMarker函数的参数
        List<Object> args=new ArrayList<Object>();
        for(Object o:list){
            args.add((o instanceof TemplateModel) ? DeepUnwrap.unwrap((TemplateModel)o):o);
        }
        BindStatus bindStatus;
        String errorCode;
        if(args.size() !=2){
            throw new TemplateModelException("error()函数只支持参数:(BindStatus status, String errorCode)");
        }
        if(!(args.get(0) instanceof BindStatus) || !(args.get(1) instanceof String)){
            throw new TemplateModelException("error()函数只支持参数:(BindStatus status, String errorCode)");
        }
        bindStatus=(BindStatus)args.get(0);
        errorCode=(String)args.get(1);
        for(ObjectError error:bindStatus.getErrors().getAllErrors()){
            String [] codes=error.getCodes();
            if(codes==null){
                continue;
            }
            for(String code:codes){
                if(code.equals(errorCode)){
                    return error.getDefaultMessage();
                }
            }
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
