package com.example.xjlacgapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "用户信息DTO")
@Getter
@Setter
@ToString
public class UserInfoDTO {

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;

    @ApiModelProperty(value = "电话号码", required = true)
    private Long phone;

    @ApiModelProperty(value = "身份证号码", required = true)
    private Long identification;

    @ApiModelProperty(value = "e-mail")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "省份")
    private String provincial;

    @ApiModelProperty(value = "城市")
    private String city;

}
