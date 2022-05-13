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
import java.util.Date;

@ApiModel(value = "订单")
@Table(name = "acg_reply")
@Entity
@Getter
@Setter
@EqualsAndHashCode()
public class Reply {
    @Id
    @ApiModelProperty(value = "id", required = true)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "userid")
    private Long userid;

    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "createtime")
    private Date createtime;

    @ApiModelProperty(value = "评论", required = true)
    @Column(name = "reply")
    private String reply;

}
