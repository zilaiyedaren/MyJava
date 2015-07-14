package org.wuheng.mybatis.web.shiro.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-30
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */

/**
 * shiro+Freemarker配置类
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer{
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();    //To change body of overridden methods use File | Settings | File Templates.
        this.getConfiguration().setSharedVariable("shiro",new ShiroTags());
    }
}
