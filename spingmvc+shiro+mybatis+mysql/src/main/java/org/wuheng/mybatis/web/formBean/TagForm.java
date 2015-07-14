package org.wuheng.mybatis.web.formBean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
public class TagForm extends PagingAndSortingForm {
    private Long id;
    private Integer type;
    private String name;

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
}
