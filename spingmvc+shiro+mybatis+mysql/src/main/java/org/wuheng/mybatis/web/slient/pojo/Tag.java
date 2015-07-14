package org.wuheng.mybatis.web.slient.pojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class Tag {
    private Long id;
    // 用于区分不同类型的tag
    private Integer type;
    // tag内容
    private String name;
    private List<Film> films;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", type=" + type + ", name=" + name
                + ", films=" + films + "]";
    }
}
