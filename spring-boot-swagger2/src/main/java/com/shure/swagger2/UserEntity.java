package com.shure.swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="用户实体")
public class UserEntity {

    @ApiModelProperty("用户编号")
    private Integer id;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户年龄")
    private int age;

    @ApiModelProperty("用户性别")
    private boolean gender;

    @ApiModelProperty("住址")
    private String address;

}
