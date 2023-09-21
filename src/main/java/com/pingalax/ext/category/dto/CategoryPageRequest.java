package com.pingalax.ext.category.dto;

import com.pingalax.common.util.page.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 分页请求参数
 * @date 2023-09-19 11:19
 */
@Data
public class CategoryPageRequest extends BasePage {
    @ApiModelProperty(value = "类型")
    private Integer type;
}
