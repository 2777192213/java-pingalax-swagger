package com.pingalax.ext.dish.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 分页查找响应结果
 * @date 2023-09-21 10:45
 */
@Data
public class DishPageResponse extends Dish {
    @ApiModelProperty("分类名称")
    private String categoryName;
}
