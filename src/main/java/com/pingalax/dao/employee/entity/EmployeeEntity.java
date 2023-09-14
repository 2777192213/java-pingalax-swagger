package com.pingalax.dao.employee.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author zhouxiaotao
 * @Description: 管理员实体类
 * @date 2023-08-31 21:04
 */
@Data
@TableName("Employee")
public class EmployeeEntity {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private LocalTime updateTime;
    private String updateUser;
    private Integer status;
}
