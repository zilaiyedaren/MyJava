package org.wuheng.mybatis.web.utils;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
public class DataGridResult {
    private int total;
    private Object rows;
    private Object footer;

    public DataGridResult() {
    }

    public int getTotal() {
        return total;
    }

    public DataGridResult(Object rows) {
        this.rows = rows;
    }

    public DataGridResult(int total, Object rows) {
        this.total = total;
        this.rows = rows;
    }

    public DataGridResult(int total, Object rows, Object footer) {
        this.total = total;
        this.rows = rows;
        this.footer = footer;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Object getFooter() {
        return footer;
    }

    public void setFooter(Object footer) {
        this.footer = footer;
    }
}
