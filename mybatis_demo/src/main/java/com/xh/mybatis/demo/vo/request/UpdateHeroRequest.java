package com.xh.mybatis.demo.vo.request;

import lombok.Data;

/**
 *
 * @author xionghao
 * @date 2017/12/15
 */
@Data
public class UpdateHeroRequest {
    private Integer id;
    private String name;
    private Double price;
    private String sex;
    private String type;
}
