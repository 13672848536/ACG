package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(value = "省份")
@Table(name = "provincial")
@Getter
@Setter
@EqualsAndHashCode()
public class Provincial {

    @ApiModelProperty(value = "城市", required = true)
    @Column(name = "provincial")
    private String provincial;

    @ApiModelProperty(value = "省份id", required = true)
    @Column(name = "pid")
    private int pid;

}
