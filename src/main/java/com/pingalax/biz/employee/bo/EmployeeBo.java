package com.pingalax.biz.employee.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author zhouxiaotao
 * @Description: 登录业务实现成实体
 * @date 2023-08-31 21:28
 */
@Data
public class EmployeeBo {
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
