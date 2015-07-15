package org.myself.mobile.web.bean.param;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-30
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */
public class JsonParam {

    private String toUser;

    private String templateId;

    private String url;

    private String topColor;

    private Map<String,Map> data;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public Map<String, Map> getData() {
        return data;
    }

    public void setData(Map<String, Map> data) {
        this.data = data;
    }
}
