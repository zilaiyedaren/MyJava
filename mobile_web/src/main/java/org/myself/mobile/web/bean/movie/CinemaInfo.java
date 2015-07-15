package org.myself.mobile.web.bean.movie;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-19
 * Time: 下午12:48
 * To change this template use File | Settings | File Templates.
 */
public class CinemaInfo {
//    name 	string 	poi名称
//    uid 	string 	pid id
//    address 	string 	poi地址
//    telephone 	string 	poi电话
//    location 	object 	当前poi坐标（经纬度）
//    lng 	float 	经度
//    lat 	float 	纬度
//    review 	array 	评论内容集合
//    content 	string 	评论内容
//    date 	string 	评论日期
//    movies 	array 	影片信息集合
//    movie_name 	string 	影片名称
//    movie_type 	string 	影片类型
//    movie_release_date 	string 	影片上映时间
//    movie_nation 	string 	影片所属国家
//    movie_starring 	string 	影片演员
//    movie_length 	string 	影片时长
//    movie_picture 	string 	影片图片地址
//    movie_score 	string 	影片评分
//    movie_tags 	string 	影片所属类型
//    movie_message 	string 	影片概要信息
//    movie_description 	string 	剧情介绍
//    time_table 	array 	电影放映信息集合
//    date 	string 	影片放映日期
//    time 	string 	影片放映时间
//    lan 	string 	影片语言
//    type 	string 	影片类型
//    price 	float 	影片价格
    private String address;

    private String name;

    private String telphone;

    private List<MovieInfo> movie;

    private List<Review> review;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public List<MovieInfo> getMovie() {
        return movie;
    }

    public void setMovie(List<MovieInfo> movie) {
        this.movie = movie;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
}
