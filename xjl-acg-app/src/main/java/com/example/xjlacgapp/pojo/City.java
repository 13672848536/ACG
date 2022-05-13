package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(value = "城市")
@Table(name = "City")
@Getter
@Setter
@EqualsAndHashCode()
public class City {

    @ApiModelProperty(value = "城市id", required = true)
    @Column(name = "cid")
    private int cid;

    @ApiModelProperty(value = "城市", required = true)
    @Column(name = "city")
    private String city;

    @ApiModelProperty(value = "省份id", required = true)
    @Column(name = "pid")
    private int pid;

}
