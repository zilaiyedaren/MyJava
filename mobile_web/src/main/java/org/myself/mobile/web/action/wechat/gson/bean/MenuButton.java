package org.myself.mobile.web.action.wechat.gson.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 13-11-25
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class MenuButton extends Button {

    private String type;

    private String name;

    private String key;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
