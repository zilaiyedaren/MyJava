package org.wuheng.framework.lucene5.suggest;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午9:53
 * To change this template use File | Settings | File Templates.
 */
public class Product implements Serializable {
    private String name;//产品名称
    private String image;//产品图片
    private String[] regions;//产品销售地区
    private int numberSold;//产品销售量

    public Product(String name, String image, String[] regions, int numberSold) {
        this.name = name;
        this.image = image;
        this.regions = regions;
        this.numberSold = numberSold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getRegions() {
        return regions;
    }

    public void setRegions(String[] regions) {
        this.regions = regions;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }
}
