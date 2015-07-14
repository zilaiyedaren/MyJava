package org.wuheng.mybatis.web.slient.pojo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:46
 * To change this template use File | Settings | File Templates.
 */
public class Music {
    private Long id;
    private String name;
    private String music_url;
    private String created;
    private Integer publiced;
    private int singer_id;

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

    public String getMusic_url() {
        return music_url;
    }

    public void setMusic_url(String music_url) {
        this.music_url = music_url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getPubliced() {
        return publiced;
    }

    public void setPubliced(Integer publiced) {
        this.publiced = publiced;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    @Override
    public String toString() {
         return "Music [id=" + id + ", name=" + name
                + ", music_url=" + music_url + ", created=" + created
                + ", publiced=" + publiced + ", singer_id=" + singer_id + "]";
                 //To change body of overridden methods use File | Settings | File Templates.
    }
}
