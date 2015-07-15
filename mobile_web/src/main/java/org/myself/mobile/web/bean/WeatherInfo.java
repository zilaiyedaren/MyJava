package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 14-1-3
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
public class WeatherInfo {

    private String city;

    private String date_y;

    private String week;

    private String temp1;

    private String weather1;

    private String wind1;

    private String index_d;

    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getWeather1() {
        return weather1;
    }

    public void setWeather1(String weather1) {
        this.weather1 = weather1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getWind1() {
        return wind1;
    }

    public void setWind1(String wind1) {
        this.wind1 = wind1;
    }

    public String getIndex_d() {
        return index_d;
    }

    public void setIndex_d(String index_d) {
        this.index_d = index_d;
    }
}
