package org.wuheng.mybatis.web.slient.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.summercool.util.StackTraceUtil;
import org.summercool.web.servlet.pipeline.ExceptionPipeline;
import org.summercool.web.views.string.StringView;
import org.wuheng.mybatis.web.slient.result.AjaxResult;
import org.wuheng.mybatis.web.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午6:14
 * To change this template use File | Settings | File Templates.
 */
public class DefaultExceptionHandler implements ExceptionPipeline {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private int order;
    @Override
    public void handleExceptionInternal(HttpServletRequest request, HttpServletResponse response,
                                        ModelAndView modelAndView, Throwable throwable) throws Exception {
        String stackTrace= StackTraceUtil.getStackTrace(throwable);
        //记录错误信息
        logger.error(stackTrace);
        if(modelAndView!=null){
            AjaxResult ajaxResult=new AjaxResult(false,stackTrace);
            StringView sv=new StringView(JsonUtil.getJsonStr(ajaxResult));
            modelAndView.setView(sv);
        }else{
            throwable.printStackTrace();
        }

    }

    @Override
    public int getOrder() {
        return order;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
