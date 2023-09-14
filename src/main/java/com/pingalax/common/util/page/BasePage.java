package com.pingalax.common.util.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zhouxiaotao
 * @Description: 分页基础信息
 * @date 2023-08-09 8:56
 */
@Getter
@Setter
public class BasePage {
    @ApiModelProperty(value = "当前页数(默认值为1)",required = true)
    @NotNull(message = "当前页数不能为空")
    @Min(value = 1L,message = "当前页数必须大于等于1")
    private Long pageIndex;


    @ApiModelProperty(value = "每页大小(默认值为10)",required = true)
    @NotNull(message = "页码大小不能为空")
    @Min(value = -1L,message = "当前页数必须大于等于-1")
    private Long pageSize;
}
