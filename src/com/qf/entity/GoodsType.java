package com.qf.entity;

import lombok.Data;

//@Data
public class GoodsType {

    private Integer id;

    private String gname;

    private String gpng;

    private Integer gpid;

    private String gpname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGpng() {
        return gpng;
    }

    public void setGpng(String gpng) {
        this.gpng = gpng;
    }

    public Integer getGpid() {
        return gpid;
    }

    public void setGpid(Integer gpid) {
        this.gpid = gpid;
    }

    public String getGpname() {
        return gpname;
    }

    public void setGpname(String gpname) {
        this.gpname = gpname;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", gname='" + gname + '\'' +
                ", gpng='" + gpng + '\'' +
                ", gpid=" + gpid +
                ", gpname='" + gpname + '\'' +
                '}';
    }
    //    public Object getGname() {
//    }
}
