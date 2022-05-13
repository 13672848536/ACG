package com.example.xjlacgapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;

@ApiModel(value = "商品DTO")
@Getter
@Setter
@ToString
public class ProductDTO {

    @ApiModelProperty(value = "用户id", required = true)
    private String id;

    @ApiModelProperty(value = "商品名称", required = true)
    private String product;

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "详细介绍", required = true)
    private String content;

    @ApiModelProperty(value = "类型", required = true)
    private String type;

    @ApiModelProperty(value = "价格", required = true)
    private int price;

    @ApiModelProperty(value = "库存", required = true)
    private int stock;
}
