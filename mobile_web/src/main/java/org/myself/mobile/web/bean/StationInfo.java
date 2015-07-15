package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-18
 * Time: 下午8:35
 * To change this template use File | Settings | File Templates.
 */
public class StationInfo {

    private String lineName;

    private String stationName;

    private String stationXy;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationXy() {
        return stationXy;
    }

    public void setStationXy(String stationXy) {
        this.stationXy = stationXy;
    }
}
