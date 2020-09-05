package com.qf.entity;

import lombok.Data;

import java.util.Date;

//@Data
public class Order {

    private Integer id;

    private String name;

    private String address;

    private String phone;

    private String sendType;

    private String payType;

    // 1:未支付，2:已支付，3:取消订单，4：xxx
    private Integer status; // 订单状态

    private Integer uid;

    private Date createTime;

    private Double totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", sendType='" + sendType + '\'' +
                ", payType='" + payType + '\'' +
                ", status=" + status +
                ", uid=" + uid +
                ", createTime=" + createTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
    //    public void setStatus(int i) {
//    }
}
