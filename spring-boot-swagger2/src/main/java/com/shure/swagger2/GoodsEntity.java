package com.shure.swagger2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="商品实体")
public class GoodsEntity {

    @ApiModelProperty(value = "商品编号", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品名称", position = 2)
    private String name;

    @ApiModelProperty(value = "商品描述", position = 3)
    private String desc;

}
