package com.pingalax.basic.site.dto;

import com.pingalax.common.util.page.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouxiaotao
 * @Description: 分页查询附件请求参数
 * @date 2023-08-09 9:39
 */
@Data
@ApiModel("分页查询站点请求参数")
@EqualsAndHashCode(callSuper = true)
public class QuerySitePageRequest extends BasePage {
    @ApiModelProperty(value = "站点状态")
    private Integer status;
}
