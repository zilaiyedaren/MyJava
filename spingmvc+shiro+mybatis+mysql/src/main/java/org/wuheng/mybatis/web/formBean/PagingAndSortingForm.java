package org.wuheng.mybatis.web.formBean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-28
 * Time: 下午7:04
 * To change this template use File | Settings | File Templates.
 */
public class PagingAndSortingForm {
    //第几页
    protected int page = 1;

    //每页记录数
    protected int rows = 10;

    //排序字段
    protected String sort;

    //排序方式
    protected String order;

    //起始数
    protected int offset;

    public PagingAndSortingForm() {
    }

    public PagingAndSortingForm(int page, int rows, String sort, String order) {
        this.page = page;
        this.rows = rows;
        this.sort = sort;
        this.order = order;
    }

    public void initOffset(){
        this.offset=(this.page-1)*this.rows;
    }
    private int validateRows(int rows){
        if(rows<=0){
            return this.rows;
        }
        return rows;
    }
    private int validatePage(int page){
        if(page<=0){
            return this.page;
        }
        return page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PagingAndSortingForm [page=" + page + ", rows=" + rows + ", sort=" + sort + ", order=" + order + "]";
    }
}
