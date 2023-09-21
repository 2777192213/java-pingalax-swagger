package com.pingalax.biz.dish.bo;

import com.pingalax.common.util.page.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouxiaotao
 * @Description: 分页查找实体
 * @date 2023-09-21 9:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DishPageRequestBo extends BasePage {
    private DishBo dishBo;
}
