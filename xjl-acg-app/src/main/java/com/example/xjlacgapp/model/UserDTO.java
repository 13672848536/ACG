package com.example.xjlacgapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "用户DTO")
@Getter
@Setter
@ToString
public class UserDTO {
    @ApiModelProperty(value = "账号", required = true)
    private String nickname;

    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
