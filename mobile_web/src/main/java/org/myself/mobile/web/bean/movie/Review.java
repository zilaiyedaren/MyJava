package org.myself.mobile.web.bean.movie;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-19
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class Review {  //电影评论
    private String content;
    private String date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
