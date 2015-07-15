package org.myself.mobile.web.bean;

import java.lang.reflect.Array;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-18
 * Time: 下午8:37
 * To change this template use File | Settings | File Templates.
 */
public class TransferInfo {
//    result_num 	int 	结果数量
//    dist 	int 	总距离 单位：米
//    stats 	int 	估计耗费时间 单位：分钟
//    foot_dist 	int 	总步行距离 单位：米
//    last_foot_dist 	int 	从终点站走到终点的距离 单位：米
//    start_stat 	string 	起点站名称
//    start_end 	string 	终点站名称
//    line_name 	string 	线路名称
//    stats 	string 	沿途站点 半角分号;（英文分号）分隔
//    line_dist 	int 	行驶距离 单位：米
//    foot_dist 	int 	步行距离 单位：米
    private String result_num;

    private int  dist;

    private String  stats;

    private int time;

    private int foot_dist;

    private int last_foot_dist;

    private String start_stat;

    private String start_end;

    private String line_name;

    private int line_dist;

    private int dist_end;

//    private Array segments;

    public String getResult_num() {
        return result_num;
    }

    public void setResult_num(String result_num) {
        this.result_num = result_num;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFoot_dist() {
        return foot_dist;
    }

    public void setFoot_dist(int foot_dist) {
        this.foot_dist = foot_dist;
    }

    public int getLast_foot_dist() {
        return last_foot_dist;
    }

    public void setLast_foot_dist(int last_foot_dist) {
        this.last_foot_dist = last_foot_dist;
    }

    public String getStart_stat() {
        return start_stat;
    }

    public void setStart_stat(String start_stat) {
        this.start_stat = start_stat;
    }

    public String getStart_end() {
        return start_end;
    }

    public void setStart_end(String start_end) {
        this.start_end = start_end;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public int getLine_dist() {
        return line_dist;
    }

    public void setLine_dist(int line_dist) {
        this.line_dist = line_dist;
    }

    public int getDist_end() {
        return dist_end;
    }

    public void setDist_end(int dist_end) {
        this.dist_end = dist_end;
    }
}
