package org.myself.mobile.web.http.rmi;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-6
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class Person implements Serializable {
        private int id;
        private String name;
        private int age;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public int getAge() {
            return age;
        }
}
