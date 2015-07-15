package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-25
 * Time: 下午2:45
 * To change this template use File | Settings | File Templates.
 */
public class PlaneAirLine {

   private String companyName;

    private String  airlineCode;

    private String startDrome;

    private String arriveDrome;

    private String startTime;

    private String arriveTime;

    private String  mode;//机型

    private String  airlineStop;//经停

    private String week;//飞行周期

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getAirlineStop() {
        return airlineStop;
    }

    public void setAirlineStop(String airlineStop) {
        this.airlineStop = airlineStop;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getArriveDrome() {
        return arriveDrome;
    }

    public void setArriveDrome(String arriveDrome) {
        this.arriveDrome = arriveDrome;
    }

    public String getStartDrome() {
        return startDrome;
    }

    public void setStartDrome(String startDrome) {
        this.startDrome = startDrome;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
