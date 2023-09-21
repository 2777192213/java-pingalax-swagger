package com.pingalax.ext.dish.dto;

import com.pingalax.common.util.page.BasePage;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 分页查找实体
 * @date 2023-09-21 9:45
 */
@Data
public class DishPageRequest extends BasePage {
    private Dish dish;
}
