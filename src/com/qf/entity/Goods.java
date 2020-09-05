package com.qf.entity;

import lombok.Data;

//@Data
public class Goods {

    private Integer id;

    private String gname;

    private String gdesc;

    private Double gprice;

    private Integer gnum;

    private Double gpriceOff;

    private String gpng;

    private Integer gpid; // 大类
    private String gpname;

    private  Integer gfid; // 小类
    private String gfname;

    private String fileName;

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

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public Double getGpriceOff() {
        return gpriceOff;
    }

    public void setGpriceOff(Double gpriceOff) {
        this.gpriceOff = gpriceOff;
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

    public Integer getGfid() {
        return gfid;
    }

    public void setGfid(Integer gfid) {
        this.gfid = gfid;
    }

    public String getGfname() {
        return gfname;
    }

    public void setGfname(String gfname) {
        this.gfname = gfname;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", gname='" + gname + '\'' +
                ", gdesc='" + gdesc + '\'' +
                ", gprice=" + gprice +
                ", gnum=" + gnum +
                ", gpriceOff=" + gpriceOff +
                ", gpng='" + gpng + '\'' +
                ", gpid=" + gpid +
                ", gpname='" + gpname + '\'' +
                ", gfid=" + gfid +
                ", gfname='" + gfname + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
