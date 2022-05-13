package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(value = "订单")
@Entity
@Table(name = "acg_order")
@Getter
@Setter
@EqualsAndHashCode()
public class Order {
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
    @Column(name = "total")
    private int total;

    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "createtime")
    private Date createtime;

    @ApiModelProperty(value = "状态", required = true)
    @Column(name = "state")
    private String state;

    @ApiModelProperty(value = "类型", required = true)
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "大小", required = true)
    @Column(name = "size")
    private String size;

    @ApiModelProperty(value = "月份", required = true)
    @Column(name = "month")
    private int month;

    @ApiModelProperty(value = "类型", required = true)
    @Column(name = "style")
    private String style;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
