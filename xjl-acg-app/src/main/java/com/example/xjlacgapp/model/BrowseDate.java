package com.example.xjlacgapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@ApiModel(value = "浏览数据")
@Getter
@Setter
@ToString
public class BrowseDate {

    @ApiModelProperty(value = "id", required = true)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private String createtime;


    @ApiModelProperty(value = "标题", required = true)
    private String title;


    @ApiModelProperty(value = "图片", required = true)
    private String img;


    @ApiModelProperty(value = "购买量", required = true)
    private int amount;

    @ApiModelProperty(value = "商品id", required = true)
    private Long productid;

    @ApiModelProperty(value = "价格", required = true)
    private int price;


    @ApiModelProperty(value = "店名", required = true)
    private String username;

    @ApiModelProperty(value = "数量", required = true)
    private int num;

    @ApiModelProperty(value = "状态", required = true)
    private String state;

    @ApiModelProperty(value = "商品名称", required = true)
    private String product;

    @ApiModelProperty(value = "评论", required = true)
    private String comment;

    @ApiModelProperty(value = "商品类型", required = true)
    private String type;

    @ApiModelProperty(value = "大小", required = true)
    private String size;
    @ApiModelProperty(value = "回复状态", required = true)
    private Boolean value;
}
