package org.myself.mobile.web.action.wechat.gson.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 13-11-25
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class ComplexButton extends Button {

    private MenuButton[] sub_button;

    private String name;

    public MenuButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(MenuButton[] sub_button) {
        this.sub_button = sub_button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
