package org.wuheng.mybatis.web.slient.pojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */
public class Film {

    private Long id;

    private String name;

    private String duration;

    private String introduction;

    private String actor;

    private String director;

    private Float star;

    private String country;

    private String screen;

    private String type;

    private String coverUrl;

    private Long countryId;

    private Long screenId;

    private Integer publiced;
//    //分集信息
//    private List<FilmSection> filmSections;
//    private List<Tag> tags;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public Integer getPubliced() {
        return publiced;
    }

    public void setPubliced(Integer publiced) {
        this.publiced = publiced;
    }

//    public List<FilmSection> getFilmSections() {
//        return filmSections;
//    }
//
//    public void setFilmSections(List<FilmSection> filmSections) {
//        this.filmSections = filmSections;
//    }
//
//    public List<Tag> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Tag> tags) {
//        this.tags = tags;
//    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", name=" + name + ", duration=" + duration
                + ", introduction=" + introduction + ", actor=" + actor
                + ", director=" + director + ", star=" + star + ", country="
                + country + ", screen=" + screen + ", type=" + type
                + ", coverUrl=" + coverUrl + ", countryId=" + countryId
                + ", screenId=" + screenId + ", publiced=" + publiced+"]";
//                + ", filmSections=" + filmSections + ", tags=" + tags + "]";
    }
}
