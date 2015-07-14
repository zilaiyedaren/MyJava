package org.wuheng.mybatis.web.slient.result;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-28
 * Time: 下午6:42
 * To change this template use File | Settings | File Templates.
 */
public class AjaxResult {
    private boolean success;
    private String message;

    public static AjaxResult SUCCESS=new AjaxResult(true);
    private static AjaxResult FAILURE=new AjaxResult(false);

    public AjaxResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AjaxResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
