package com.pingalax.ext.users.dto;

import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 接口层实体
 * @date 2023-09-22 15:20
 */
@Data
public class User {
    private static final long serialVersionUID = 1L;

    private Long id;


    //姓名
    private String name;


    //手机号
    private String phone;


    //性别 0 女 1 男
    private String sex;


    //身份证号
    private String idNumber;


    //头像
    private String avatar;


    //状态 0:禁用，1:正常
    private Integer status;
}
