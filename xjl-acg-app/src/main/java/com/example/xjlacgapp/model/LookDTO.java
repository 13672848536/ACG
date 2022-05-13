package com.example.xjlacgapp.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "可视化数据")
@Getter
@Setter
@ToString
public class LookDTO {
     private String name;
     private Long value;
}
