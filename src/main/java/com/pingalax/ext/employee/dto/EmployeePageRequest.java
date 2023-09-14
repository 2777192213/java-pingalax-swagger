package com.pingalax.ext.employee.dto;

import com.pingalax.common.util.page.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 分页查询员工请求实体类
 * @date 2023-09-08 13:16
 */
@Data
public class EmployeePageRequest extends BasePage {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;
}
