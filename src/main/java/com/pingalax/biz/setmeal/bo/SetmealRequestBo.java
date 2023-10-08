package com.pingalax.biz.setmeal.bo;

import com.pingalax.ext.setmealdish.dto.SetmealDish;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 套餐业务层实体类
 * @date 2023-09-21 21:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SetmealRequestBo extends SetmealBo {

    private List<SetmealDish> setmealDishList;

    private String categoryName;
}
