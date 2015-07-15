package org.myself.mobile.web.http.httphessian;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-2
 * Time: 下午7:04
 * To change this template use File | Settings | File Templates.
 */
//该类必须序列化
//@XmlRootElement(name = "WeatherInfo")
public class WeatherInfo implements Serializable{

    private final static long serialVersionUID = 12343L;
//        @XmlElement(required = true)

//        @XmlElement(required = true)
    private String city;

    private String cityid;

    private String temp1;

    private String temp2;

    private String weather;

    private String ptime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
