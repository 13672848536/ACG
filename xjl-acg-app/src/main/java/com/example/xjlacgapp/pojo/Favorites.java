package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(value = "收藏")
@Table(name = "acg_favorites")
@Getter
@Setter
@EqualsAndHashCode()
public class Favorites {

    @ApiModelProperty(value = "商品id", required = true)
    @Column(name = "productid")
    private Long productid;

    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "userid")
    private Long userid;

}
