package org.wuheng.mybatis.web.utils;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class ResultUtil {
    /**
     * 执行成功(String)
     */
    public static String SUCCESS = "{\"result\":\"success\"}";
    /**
     * 执行失败(String)
     */
    public static String FAILURE = "{\"result\":\"failure\"}";
    /**
     * 执行成功(Boolean)
     */
    public static String TRUE = "{\"result\":true}";
    /**
     * 执行失败(Boolean)
     */
    public static String FALSE = "{\"result\":false}";
    /**
     * 系统异常（String）
     */
    public static String ERROR = "{\"result\":\"err\"}";
    /**
     * 参数绑定异常
     */
    public static String BIND_ERROR = "{\"result\":\"bindError\"}";
    /**
     * 空 （String）
     */
    public static String EMPTY = "";

    public static void main(String[] args) {
        System.out.println(ResultUtil.SUCCESS);
        System.out.println(ResultUtil.FAILURE);
    }
}
