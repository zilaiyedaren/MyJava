package org.wuheng.mybatis.web.slient.config.security;

import org.apache.shiro.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSecurity {
    private List<String> filterPaths=new ArrayList<String>();
    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    private UrlPathHelper urlPathHelper=new UrlPathHelper();

    protected AbstractSecurity() {
        urlPathHelper.setUrlDecode(false);
    }

    public List<String> getFilterPaths() {
        return filterPaths;
    }

    public void setFilterPaths(List<String> filterPaths) {
        this.filterPaths = filterPaths;
    }

    public boolean match(HttpServletRequest request){
        for(String path:filterPaths){
            if(matchHelp(path,request)){
                return true;
            }
        }
        return false;
    }

    public boolean match(String url){
        for(String path:filterPaths){
            if(antPathMatcher.match(path,url)){
                return true;
            }
        }
        return false;
    }
    public boolean match(HttpServletRequest request,List<String> filterPaths){
        for(String path:filterPaths){
            if(matchHelp(path,request)){
                return true;
            }
        }
        return false;
    }

    public UrlPathHelper getUrlPathHelper() {
        return urlPathHelper;
    }

    private boolean matchHelp(String pattern,HttpServletRequest request){
        return antPathMatcher.match(pattern,urlPathHelper.getLookupPathForRequest(request));
    }
}
