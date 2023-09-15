package com.pingalax.biz.employee.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private Integer status;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String updateUser;
    private String createUser;
}
