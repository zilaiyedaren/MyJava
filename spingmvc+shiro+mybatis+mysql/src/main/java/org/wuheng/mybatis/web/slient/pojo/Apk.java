package org.wuheng.mybatis.web.slient.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:46
 * To change this template use File | Settings | File Templates.
 */
public class Apk {
    private Long id;
    private String name;
    private String pack_name;
    private String introduction;
    private String icon_url;
    private int version_code;
    private int type;
    private String created;
    private Integer publiced;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPack_name() {
        return pack_name;
    }

    public void setPack_name(String pack_name) {
        this.pack_name = pack_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getPubliced() {
        return publiced;
    }

    public void setPubliced(Integer publiced) {
        this.publiced = publiced;
    }

    @Override
    public String toString() {
        return "Apk [id=" + id + ", name=" + name
                + ", pack_name=" + pack_name + ", introduction=" + introduction
                + ", icon_url=" + icon_url + ", version_code=" + version_code
                +",type="+type+",created="+created+ ", publiced=" + publiced+"]";
                    //To change body of overridden methods use File | Settings | File Templates.
    }
}
