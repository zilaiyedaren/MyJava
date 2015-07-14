package org.myself.web.spring.utils;
/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-12-1
 * Time: 下午9:11
 * To change this template use File | Settings | File Templates.
 */
import java.util.List;
/**
 * 封装分页对象
 * @param <T>
 */
public class PageView<T>  {
    private List<T> pageList;
    private int totalNo;//总记录数
    private int cp; //当前页
    private int ls;//每页显示记录数
    String keyword="";

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public int getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getLs() {
        return ls;
    }

    public void setLs(int ls) {
        this.ls = ls;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
