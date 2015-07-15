package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-18
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
public class MobilePhone {
//    {"status":200,"message":"OK","data":{"province":"浙江","city":"杭州","areaCode":"0571","postCode":"310000","corp":"中国移动","card":null}}
    private String phoneNum;
    private String  province;
    private String  city;
    private String  areaCode;
    private String  postCode;
    private String  corp;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }
}
