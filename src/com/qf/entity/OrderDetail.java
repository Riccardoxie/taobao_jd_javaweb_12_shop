package com.qf.entity;

import lombok.Data;

//@Data
public class OrderDetail {

    private Integer id;

    private Integer oid;

    private Integer gid;

    private String gname;

    private Double gprice;

    private String gpng;

    private String gdesc;

    private Integer count;

    private Double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public String getGpng() {
        return gpng;
    }

    public void setGpng(String gpng) {
        this.gpng = gpng;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", oid=" + oid +
                ", gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gpng='" + gpng + '\'' +
                ", gdesc='" + gdesc + '\'' +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
    //    public void setGname(String gname) {
//    }

//    public void setGdesc(String gdesc) {
//    }
}
