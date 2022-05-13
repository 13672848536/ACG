package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "商品")
@Entity
@Table(name = "acg_product")
@Getter
@Setter
@EqualsAndHashCode()
public class Product {
    @Id
    @ApiModelProperty(value = "id", required = true)
    @Column(name = "id")
    private Long id;
    @ApiModelProperty(value = "商品名称", required = true)
    @Column(name = "product")
    private String product;
    @ApiModelProperty(value = "标题", required = true)
    @Column(name = "title")
    private String title;
    @ApiModelProperty(value = "详细介绍", required = true)
    @Column(name = "content")
    private String content;
    @ApiModelProperty(value = "点击量", required = true)
    @Column(name = "apv")
    private int apv;
    @ApiModelProperty(value = "图片", required = true)
    @Column(name = "img")
    private String img;
    @ApiModelProperty(value = "类型", required = true)
    @Column(name = "type")
    private String type;
    @ApiModelProperty(value = "购买量", required = true)
    @Column(name = "amount")
    private int amount;
    @ApiModelProperty(value = "商品id", required = true)
    @Column(name = "productid")
    private Long productid;
    @ApiModelProperty(value = "价格", required = true)
    @Column(name = "price")
    private int price;
    @ApiModelProperty(value = "库存", required = true)
    @Column(name = "stock")
    private int stock;
    @ApiModelProperty(value = "店名", required = true)
    @Column(name = "username")
    private String username;
    @ApiModelProperty(value = "号码", required = true)
    private int rownumber;

    @ApiModelProperty(value = "状态", required = true)
    @Column(name = "state")
    private String state;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
