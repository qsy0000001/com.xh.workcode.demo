package com.xh.mybatis.demo.dao.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class Hero {
    private Integer id;

    private String name;

    private Double price;

    private String sex;

    private String type;

    private Date createTime;

}