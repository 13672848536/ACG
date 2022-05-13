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

@ApiModel(value = "收藏")
@Table(name = "acg_favorites")
@Entity
@Getter
@Setter
@EqualsAndHashCode()
public class ShoppingCart {
    @Id
    @ApiModelProperty(value = "id", required = true)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "商品id", required = true)
    @Column(name = "productid")
    private Long productid;

    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "userid")
    private Long userid;

    @ApiModelProperty(value = "数量", required = true)
    @Column(name = "amount")
    private int amount;

    @ApiModelProperty(value = "类型", required = true)
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "大小", required = true)
    @Column(name = "size")
    private String size;
}
