package com.pingalax.biz.dish.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouxiaotao
 * @Description: 菜品响应结果
 * @date 2023-09-21 10:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DishPageResponseBo extends DishBo {
    private String categoryName;
}
