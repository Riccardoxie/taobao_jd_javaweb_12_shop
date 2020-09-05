package com.qf.entity;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

@Data
public class Address {

    private Integer id;

    private String name;

    private String phone;

    private String address;

    private Integer isDef;

    private Integer uid;
}
