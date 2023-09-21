package com.pingalax.biz.category.bo;

import com.pingalax.common.util.page.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 实现层分页查找实体
 * @date 2023-09-19 11:28
 */
@Data
public class CategoryPageBo extends BasePage {
    @ApiModelProperty(value = "类型")
    private Integer type;
}
