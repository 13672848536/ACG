package com.example.xjlacgapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@ApiModel(value = "订单数据")
@Getter
@Setter
@ToString
public class OrderDate {
    @ApiModelProperty(value = "用户id", required = true)
    private String userid;

    @ApiModelProperty(value = "商品id", required = true)
    private String productid;

    @ApiModelProperty(value = "数量", required = true)
    private int total;

    @ApiModelProperty(value = "类型", required = true)
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "大小", required = true)
    @Column(name = "size")
    private String size;
}
