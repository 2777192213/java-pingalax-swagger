package com.pingalax.ext.employee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhouxiaotao
 * @Description: 登录请求参数实体类
 * @date 2023-08-31 21:49
 */
@Data
public class EmployeeRequest {
    @NotNull
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull
    private String password;
}
