package org.wuheng.mybatis.web.slient.pojo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class Novel {
    public Long id;
    // 小说名称
    private String name;
    // 小说作者
    private String author;
    // 小说状态
    private Integer status;
    // 封面图
    private String coverUrl;
    // 小说地址
    private String novelUrl;
    // 简介
    private String introduction;
    // 创建时间
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getNovelUrl() {
        return novelUrl;
    }

    public void setNovelUrl(String novelUrl) {
        this.novelUrl = novelUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Novel [id=" + id + ", name=" + name + ", author=" + author
                + ", status=" + status + ", coverUrl=" + coverUrl
                + ", novelUrl=" + novelUrl + ", introduction=" + introduction + ", created="
                + created +"]";
    }
}
