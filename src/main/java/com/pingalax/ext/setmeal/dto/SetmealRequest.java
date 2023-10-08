package com.pingalax.ext.setmeal.dto;

import com.pingalax.ext.setmealdish.dto.SetmealDish;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhouxiaotao
 * {@code @Description:} 套餐菜品请求实体类
 * {@code @date} 2023-09-21 21:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SetmealRequest extends Setmeal {

    @ApiModelProperty("套餐菜品列表")
    private List<SetmealDish> setmealDishList;

    @ApiModelProperty("分类名")
    private String categoryName;
}
