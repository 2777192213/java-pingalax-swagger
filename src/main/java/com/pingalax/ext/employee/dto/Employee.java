package com.pingalax.ext.employee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalTime;


/**
 * @author zhouxiaotao
 * @Description: 登录实体类
 * @date 2023-08-31 20:52
 */
@Data
public class Employee {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "身份证号")
    private String idNumber;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "更新时间")
    private LocalTime updateTime;
    @ApiModelProperty(value = "更新人")
    private String updateUser;

}
