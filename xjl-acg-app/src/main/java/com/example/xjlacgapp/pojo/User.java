package com.example.xjlacgapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "用户")
@Entity
@Table(name = "acg_users")
@Getter
@Setter
@EqualsAndHashCode()

public class User {
    @ApiModelProperty(value = "id", required = true)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "姓名", required = true)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "密码", required = true)
    @Column(name = "password")
    private String password;

    @ApiModelProperty(value = "用户类型", required = true)
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "昵称", required = true)
    @Column(name = "nickname")
    private String nickname;

    @ApiModelProperty(value = "电话号码", required = true)
    @Column(name = "phone")
    private Long phone;

    @ApiModelProperty(value = "身份证号码", required = true)
    @Column(name = "identification")
    private Long identification;

    @ApiModelProperty(value = "e-mail", required = true)
    @Column(name = "e-mail")
    private String email;

    @ApiModelProperty(value = "地址", required = true)
    @Column(name = "address")
    private String address;

    @ApiModelProperty(value = "图像", required = true)
    @Column(name = "img")
    private String img;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Long year;

    @ApiModelProperty(value = "省份")
    private String provincial;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "比例")
    private Long value;
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
