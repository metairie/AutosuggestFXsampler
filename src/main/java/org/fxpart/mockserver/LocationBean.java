package org.fxpart.mockserver;

/**
 * Created by metairie on 22-Jun-15.
 */
public class LocationBean implements CodeNameBean {

    private long id;
    private String code;
    private String name;

    public LocationBean() {
    }

    public LocationBean(long lid, String scode, String sname) {
        id = lid;
        code = scode;
        name = sname;
    }

    public LocationBean(String k, String v) {
        this.code = k;
        this.name = v;
    }

    public LocationBean(LocationBean locationBean) {
        id = locationBean.id;
        code = locationBean.code;
        name = locationBean.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code + "," + name + " locationbean[#" + hashCode() + "] ";
    }
}
