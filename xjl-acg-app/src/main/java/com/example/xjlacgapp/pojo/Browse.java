package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(value = "订单")
@Table(name = "acg_browse")
@Getter
@Setter
@EqualsAndHashCode()
public class Browse {

    @ApiModelProperty(value = "商品id", required = true)
    @Column(name = "productid")
    private Long productid;

    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "userid")
    private Long userid;

    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "createtime")
    private Date createtime;

    @ApiModelProperty(value = "用户年龄", required = true)
    @Column(name = "age")
    private Long age;

    @ApiModelProperty(value = "商品类型", required = true)
    @Column(name = "type")
    private String type;
}
