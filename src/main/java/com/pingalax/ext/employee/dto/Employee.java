package com.pingalax.ext.employee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @ApiModelProperty(value = "姓名",required = true)
    private String name;
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "电话",required = true)
    private String phone;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "身份证号",required = true)
    private String idNumber;
    @ApiModelProperty(value = "状态",required = true)
    private String status;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "创建人员")
    private String createUser;
    @ApiModelProperty(value = "更新人员")
    private String updateUser;

}
