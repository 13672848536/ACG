package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(value = "商品类型")
@Table(name = "acg_producttype")
@Getter
@Setter
@EqualsAndHashCode()
public class ProductType {

    @ApiModelProperty(value = "商品id", required = true)
    @Column(name = "productid")
    private Long productid;

    @ApiModelProperty(value = "类型", required = true)
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "大小", required = true)
    @Column(name = "size")
    private String size;

    @ApiModelProperty(value = "库存", required = true)
    @Column(name = "stock")
    private int stock;

}
