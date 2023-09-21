package com.pingalax.biz.dish.bo;

import com.pingalax.dao.flavor.entity.FlavorEntity;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 菜品口味实体
 * @date 2023-09-20 16:06
 */
@Data
public class DishRequestBo extends DishBo {
    //口味
    private FlavorEntity flavorEntity;
    //类名
    private String categoryName;

    private Integer copies;
}
