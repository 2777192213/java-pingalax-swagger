package com.pingalax.ext.dish.dto;

import com.pingalax.dao.flavor.entity.FlavorEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 菜品Data Transfer Object
 * @date 2023-09-20 16:03
 */
@Data
public class DishRequest extends Dish {
    //口味
    @ApiModelProperty("口味实体类")
    private FlavorEntity flavorEntity;
    //类名
    private String categoryName;

    private Integer copies;
}
