package com.xh.mybatis.demo.vo.request;

import lombok.Data;

/**
 *
 * @author xionghao
 * @date 2017/12/12
 */
@Data
public class AddHeroRequest {
    private String name;
    private Double price;
    private String sex;
    private String type;
}
