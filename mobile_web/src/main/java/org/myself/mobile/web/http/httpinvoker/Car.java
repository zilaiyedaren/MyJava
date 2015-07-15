package org.myself.mobile.web.http.httpinvoker;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-4
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
public class Car implements Serializable {

    private static final long serialVersionUID = -1115598660168001267L;

    private String name;

    private transient Integer capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
